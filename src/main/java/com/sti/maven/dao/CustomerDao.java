package com.sti.maven.dao;

import java.util.List;

import com.sti.maven.error.CustomException;
import com.sti.maven.model.Account;
import com.sti.maven.model.Customer;

public interface CustomerDao {
	
	Customer getById(Integer id) throws CustomException ;
	Customer save(Customer customer) throws CustomException;
	void delete(Customer customer) throws CustomException ;
	List<Customer> getList() throws CustomException ;
	

}
