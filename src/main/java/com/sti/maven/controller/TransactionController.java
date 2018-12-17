package com.sti.maven.controller;

import java.util.List;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sti.maven.dao.TransactionDao;
//import com.sti.maven.dao.repository.TransactionRepository;
import com.sti.maven.model.Transaction;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
	
	@Autowired
	private TransactionDao transactionDao;
	
	
	
	@GetMapping("transaction")
	public String hello(@RequestParam (value="id", defaultValue="" ) String id ) {
		try {
			Transaction transaction = transactionDao.getById(Integer.valueOf(id));
			if(transaction==null) {
				return "data tidak ditemukan" ;
			}else {
				return "Hello"+" "+ transaction.getType();
			}
		}catch (NumberFormatException e) {
			return "salah format input" ;
		}catch (SQLGrammarException e) {
			return String.format("terjadi kesalahan SQL : %s", e.getMessage());
		} 
		catch (Exception e) {
			return String.format("terjadi kesalahan Sistem : %s", e.getMessage());
		}
	}
	
	@PostMapping("/transaction")
	public Transaction insert(@RequestBody Transaction transaction ) throws Exception{
		Transaction tr = transactionDao.save(transaction);
		return tr ;
	}
	
	@PutMapping("/transaction")
	public Transaction update(@RequestBody Transaction transaction)throws Exception {
		Transaction tr = transactionDao.save(transaction);
		return tr ;
	}
	@DeleteMapping("/transaction")
	public void delete(@PathVariable ("id") Transaction transaction) throws Exception {
		transactionDao.delete(transaction);
	}
	
	@GetMapping("/transactions")
	public List<Transaction> geList() throws Exception {
		List<Transaction> list = transactionDao.getList();
		return list ;
	}
	
	
	@GetMapping("/sum/{amount}/{acc}")
	public int sum(@PathVariable ("amount") String amount,@PathVariable ("acc") int acc ) throws Exception {
	
		return  transactionDao.getSum(amount, acc) ;
	}
	
	@PatchMapping("/path/{id}")
	public Transaction patchtrx(@RequestBody Transaction transaction, @PathVariable ("id") String id ) throws Exception{
		
		try {
			Transaction cek = transactionDao.getById(Integer.valueOf(id));
			if (cek==null) {
				return null;
			}else {
				if(!StringUtils.isEmpty(transaction.getAmaountSign())) {
					cek.setAmaountSign(transaction.getAmaountSign());
				}
				if(cek.getAccountNumber() !=null){
					cek.setAccountNumber(transaction.getAccountNumber());
					
				}
				if (!StringUtils.isEmpty(transaction.getAmount())) {
					cek.setAmount(transaction.getAmount());
				}
				if (!StringUtils.isEmpty(transaction.getType())) {
					cek.setType(transaction.getType());
				}
				return transactionDao.save(cek) ;
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			
		}
		
		
	}

}
