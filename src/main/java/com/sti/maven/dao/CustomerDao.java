package com.sti.maven.dao;

import java.util.List;

import com.sti.maven.model.Customer;

public interface CustomerDao {
	
	Customer getById(Integer id) throws Exception ;
	Customer save(Customer customer) throws Exception;
	void delete(Customer customer) throws Exception ;
	List<Customer> getList() throws Exception ;
	
}
