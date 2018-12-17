package com.sti.maven.controller;

import java.util.List;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.sti.maven.dao.AccountDao;
import com.sti.maven.model.Account;
import com.sti.maven.model.Customer;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountDao accountDao;
	@GetMapping("/hello")
	public String hello(@RequestParam (value="id", defaultValue="") String id  ) {
		try {
			Account account = accountDao.getById(Integer.valueOf(id));
			if (account==null) {
				return "data account tidak ditemukan";
			}else {
				return "Hello "+" "+account.getAccountNumber() ;
				
			}
		}catch (NumberFormatException e) {
			return "Salah Format input !";
		} catch (SQLGrammarException e) {
			return String.format("Terjadi kesalahan SQL : %s ", e.getMessage());
		} catch (Exception e) {
			return String.format("Terjadi kesalahan Sistem : %s ", e.getMessage());
		}
		
	}
	
	@PostMapping("/account")
	public Account account(@RequestBody Account account) throws Exception{
		Account acc = accountDao.save(account);
		return acc;
	}
	
	@PutMapping("/account")
	public Account update(@RequestBody Account account) throws Exception {	
		
		Account ac = accountDao.save(account);
		return account;
	}
	
	@DeleteMapping("/account/{id}")
	public void delAccount (@PathVariable ("id") Account account ) throws Exception {
		accountDao.delete(account);
	}
	
	@GetMapping("/accounts")
	public List<Account> getList() throws Exception{	
		List<Account> list = accountDao.getList();
		
		return list;
	}
	
	@PatchMapping("accounts/{id}")
	public Account customerpath(@RequestBody Account account, @PathVariable ("id") String id) throws Exception  {
		try {
			Account check = accountDao.getById(Integer.valueOf(id));
			if(check==null) {
				return null ;
			}else {
				if (!StringUtils.isEmpty(account.getBalance())) {
					check.setBalance(account.getBalance());
				}
				if (!StringUtils.isEmpty(account.getOpenDate())) {
					check.setOpenDate(account.getOpenDate());
				}
				if (check.getCustomerNumber() !=null) {
					check.setCustomerNumber(account.getCustomerNumber());
				}
				
				return accountDao.save(check);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null ;
		}
		
	}
}
