package com.sti.maven.dao;

import java.util.List;

import com.sti.maven.error.CustomException;
import com.sti.maven.model.Account;
import com.sti.maven.model.Transaction;

public interface TransactionDao {

	Transaction getById(Integer id) throws CustomException ;
	Integer getSum(String amount, int acc) throws CustomException;
	Transaction save(Transaction transaction) throws CustomException;
	Transaction save(Transaction transaction, String id) throws CustomException;
	void delete(Transaction transaction) throws CustomException ;
	List<Transaction> getList() throws CustomException ;
	
	List<Transaction>getListByAccount(Account account) throws CustomException ;
}
