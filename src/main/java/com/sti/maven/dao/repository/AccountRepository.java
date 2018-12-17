package com.sti.maven.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sti.maven.model.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {
	Account findByAccountNumber(int accountNumber);

}
