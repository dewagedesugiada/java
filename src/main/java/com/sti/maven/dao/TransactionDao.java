package com.sti.maven.dao;

import java.util.List;

import com.sti.maven.model.Transaction;

public interface TransactionDao {

	Transaction getById(Integer id) throws Exception ;
	Integer getSum(String amount, int acc) throws Exception;
	Transaction save(Transaction transaction) throws Exception;
	Transaction save(Transaction transaction, String id) throws Exception;
	void delete(Transaction transaction) throws Exception ;
	List<Transaction> getList() throws Exception ;
}
