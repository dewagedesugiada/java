package com.sti.maven.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sti.maven.model.Transaction;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {

	Transaction findByIdTransaction(int IdTransaction);
	
	@Query("select sum(transaction.amount) from Transaction transaction where transaction.amaountSign=?1 and transaction.accountNumber.accountNumber=?2")
	int getSumAmount(String amountSign, int accountNumber );
}
