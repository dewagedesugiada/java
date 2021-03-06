package com.sti.maven.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sti.maven.model.Account;
import com.sti.maven.model.Transaction;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {

	Transaction findByIdTransaction(int idTransaction);
	
	List<Transaction> findByAccountNumber(Account account);
	
	@Query("select sum(transaction.amount) from Transaction transaction where transaction.amountSign=?1 and transaction.accountNumber.accountNumber=?2")
	int getSumAmount(String amountSign, int accountNumber );
}

