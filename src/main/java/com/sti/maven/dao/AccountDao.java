package com.sti.maven.dao;

import java.util.List;

import com.sti.maven.model.Account;

public interface AccountDao {
	
	Account getById(Integer id) throws Exception ;
	Account save(Account account) throws Exception;
	Account save(Account account, String id) throws Exception;
	void delete(Account account) throws Exception ;
	List<Account> getList() throws Exception ;

	
}
