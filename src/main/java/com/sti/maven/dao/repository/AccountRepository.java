package com.sti.maven.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sti.maven.model.Account;
import com.sti.maven.model.Customer;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {
	Account findByAccountNumber(int accountNumber);
	
	List<Account> findByCustomerNumber(Customer customer);

}
