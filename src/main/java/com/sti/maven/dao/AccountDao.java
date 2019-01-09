package com.sti.maven.dao;

import java.util.List;

import com.sti.maven.error.CustomException;
import com.sti.maven.model.Account;
import com.sti.maven.model.Customer;

public interface AccountDao {
	
	Account getById(Integer id) throws CustomException ;
	Account save(Account account) throws CustomException;
	Account save(Account account, String id) throws CustomException;
	void delete(Account account) throws CustomException ;
	
	List<Account> getList() throws CustomException ;
	List<Account>getListByCustomer(Customer customer) throws CustomException ;

	
}
