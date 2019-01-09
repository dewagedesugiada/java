package com.sti.maven.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.maven.dao.AccountDao;
import com.sti.maven.dao.TransactionDao;
import com.sti.maven.error.CustomException;
import com.sti.maven.model.Account;


import com.sti.maven.model.Transaction;
import com.sti.maven.model.dto.CommonResponse;
import com.sti.maven.model.dto.TransactionDto;

@RestController
@RequestMapping("/transaction")
@SuppressWarnings("rawtypes")
public class TransactionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private AccountDao accountdao ;
	
	
	
	@GetMapping(value="/{id}")
	public CommonResponse getById(@PathVariable("id") String idTransaction) throws CustomException {
		LOGGER.info("idtransaction : {}", idTransaction);
		
		try {
			Transaction transaction = transactionDao.getById(Integer.valueOf(idTransaction));
			
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
		}catch (CustomException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		}
		catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");

		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());

		}
	}
	
	@PostMapping(value="")
	public CommonResponse insert(@RequestBody TransactionDto transactionDto ) throws CustomException{
		
		try {
			Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
			transaction.setIdTransaction(0);
			transaction = transactionDao.save(transaction);
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
			
		}  catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());

		}
	}
	
	@PutMapping(value="")
	public CommonResponse update(@RequestBody TransactionDto transaction)throws CustomException {
	
		try {
			Transaction checkTransaction = transactionDao.getById(transaction.getIdTransaction());
			if (checkTransaction==null) {
				return new CommonResponse("14", "transaktion tidak ditemukan");
			}
			if (transaction.getType()!=null) {
				checkTransaction.setType(transaction.getType());
			}
			if (transaction.getAmount()!=null) {
				checkTransaction.setAmount(transaction.getAmount());
			}
			if (transaction.getAmountSign()!=null) {
				checkTransaction.setAmountSign(transaction.getAmountSign());
			}
			if (transaction.getAccountNumber()!=null) {
				checkTransaction.setAccountNumber(transaction.getAccountNumber());
			}
			
			checkTransaction = transactionDao.save(checkTransaction);
			
			
			return new CommonResponse<TransactionDto>(modelMapper.map(checkTransaction, TransactionDto.class));
		}
		catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());

		}
		
	}
	
	
	@DeleteMapping(value="/{id}")
	public CommonResponse delete(@PathVariable ("id") Integer idTransaction) throws CustomException {
		
		try {
			Transaction checkTransaction = transactionDao.getById(idTransaction);
			if (checkTransaction==null) {
				return new CommonResponse("06", "transaction tidak ditemukan");
			}
			
			transactionDao.delete(checkTransaction);
			return new CommonResponse();
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		}
		
		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());

		}
	}
	
	@GetMapping(value="/list")
	public CommonResponse geList(@RequestParam(name="accountNumber", defaultValue="")String transaction) throws CustomException {
		try {
			List<Transaction> transactions;
			if (!StringUtils.isEmpty(transaction)) {
				Account checkTransaction = accountdao.getById(Integer.parseInt(transaction));
				if (checkTransaction == null) {
					throw new CustomException("Customer tidak ditemukan !");

				}

				transactions = transactionDao.getListByAccount(checkTransaction);

			} else {
				LOGGER.info("account get list, param : {} ", transaction);
				transactions = transactionDao.getList();

			}

			return new CommonResponse<List<TransactionDto>>(
					transactions.stream().map(trx -> modelMapper.map(trx, TransactionDto.class)).collect(Collectors.toList()));

		} catch (CustomException e) {
			throw e;
		} catch (NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		}

		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());

		}
		

	}
	
	
	@GetMapping("/sum/{amount}/{acc}")
	public int sum(@PathVariable ("amount") String amount,@PathVariable ("acc") int acc ) throws CustomException {
	
		return  transactionDao.getSum(amount, acc) ;
	}
	
	
}
