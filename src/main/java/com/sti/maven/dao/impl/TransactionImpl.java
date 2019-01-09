package com.sti.maven.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;

import com.sti.maven.dao.TransactionDao;
import com.sti.maven.dao.repository.TransactionRepository;
import com.sti.maven.error.CustomException;
import com.sti.maven.model.Account;
import com.sti.maven.model.Transaction;

public class TransactionImpl extends BaseImpl implements TransactionDao {

	@Autowired
	private TransactionRepository repository ;
	@Override
	public Transaction getById(Integer id) throws CustomException {
		
		return repository.findByIdTransaction(id);
	}

	@Override
	public Transaction save(Transaction transaction) throws CustomException {
	
		return repository.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) throws CustomException {
		repository.delete(transaction);
		
	}

	@Override
	public List<Transaction> getList() throws CustomException {
		
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = critB.createQuery(Transaction.class);
		query.from(Transaction.class);
		TypedQuery<Transaction> q = em.createQuery(query);
		
		return q.getResultList();
	}

	@Override
	public Integer getSum(String amount, int acc) throws CustomException {
		
		return repository.getSumAmount(amount, acc);
	}

	@Override
	public Transaction save(Transaction transaction, String id) throws CustomException {
		
		return repository.save(transaction);
	}

	@Override
	public List<Transaction> getListByAccount(Account account) throws CustomException {
		
		return repository.findByAccountNumber(account);
	}

}
