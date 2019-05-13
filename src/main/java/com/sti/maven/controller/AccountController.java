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
import com.sti.maven.dao.CustomerDao;
import com.sti.maven.dao.TransactionDao;
import com.sti.maven.error.CustomException;
import com.sti.maven.model.Account;
import com.sti.maven.model.Customer;
import com.sti.maven.model.Transaction;
import com.sti.maven.model.dto.AccountDto;
import com.sti.maven.model.dto.CommonResponse;
import com.sti.maven.model.dto.TransactionDto;

@RestController
@RequestMapping("/account")
@SuppressWarnings("rawtypes")
public class AccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ModelMapper modelMaper;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private TransactionDao transactionDao ;

	@GetMapping("/{id}")
	public CommonResponse getById(@PathVariable("id") String accontNumber) throws CustomException {
		LOGGER.info("accountNumber : {} ", accontNumber);
		try {
			Account account = accountDao.getById(Integer.valueOf(accontNumber));
		
			return new CommonResponse<AccountDto>(modelMaper.map(account, AccountDto.class));

		} catch (CustomException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}
	
	@GetMapping("/{accountNumber}/transactions")
	public CommonResponse getTransById(@PathVariable("accountNumber") String accontNumber) throws CustomException {
		LOGGER.info("accountNumber : {} ", accontNumber);
		try {
			Account account = accountDao.getById(Integer.valueOf(accontNumber));
			List<Transaction> transactions = transactionDao.getListByAccount(account); 
			
			return new CommonResponse<List<TransactionDto>>(transactions.stream().map(trx -> modelMaper.map(trx, TransactionDto.class)).collect(Collectors.toList()));
			
		

		} catch (CustomException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}

	@PostMapping(value ="")
	public CommonResponse insert(@RequestBody AccountDto accountDto) throws CustomException {

		try {
			Account account = modelMaper.map(accountDto, Account.class);
			account.setAccountNumber(0);
			account = accountDao.save(account);
			return new CommonResponse<AccountDto>(modelMaper.map(account, AccountDto.class));
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());

		}
	}

	@PutMapping(value = "")
	public CommonResponse update(@RequestBody AccountDto account) throws CustomException {

		try {
			Account checkAccount = accountDao.getById(account.getAccountNumber());
			if (checkAccount == null) {
				return new CommonResponse("14", "Account tidak ditemukan");
			}
			if (account.getOpenDate() != null) {
				checkAccount.setOpenDate(account.getOpenDate());
			}
			if (account.getBalance() != null) {
				checkAccount.setBalance(account.getBalance());
			}
			if (account.getCustomerNumber() != null) {
				checkAccount.setCustomerNumber(account.getCustomerNumber());
			}

			checkAccount = accountDao.save(checkAccount);
			return new CommonResponse<AccountDto>(modelMaper.map(checkAccount, AccountDto.class));
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			return new CommonResponse("06", e.getMessage());
		}
	}

	@DeleteMapping("/{account}")
	public CommonResponse delete(@PathVariable("account") Integer accountNumber) throws CustomException {

		try {
			Account checkAccount = accountDao.getById(accountNumber);

			if (checkAccount == null) {
				return new CommonResponse("06", "account tidak ditemukan");
			}
			accountDao.delete(checkAccount);
			return new CommonResponse();
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		}

		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}


	@GetMapping(value = "/list")
	public CommonResponse getList(@RequestParam(name = "customerNumber", defaultValue = "") String account)
			throws CustomException {

		try {
			List<Account> accounts;
			if (!StringUtils.isEmpty(account)) {
				Customer checkCustomer = customerDao.getById(Integer.parseInt(account));
				if (checkCustomer == null) {
					throw new CustomException("Customer tidak ditemukan !");

				}

				accounts = accountDao.getListByCustomer(checkCustomer);

			} else {
				LOGGER.info("account get list, param : {} ", account);
				accounts = accountDao.getList();

			}

			return new CommonResponse<List<AccountDto>>(
					accounts.stream().map(acc -> modelMaper.map(acc, AccountDto.class)).collect(Collectors.toList()));

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

}
