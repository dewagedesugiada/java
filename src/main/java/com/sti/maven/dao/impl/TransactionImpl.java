package com.sti.maven.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.maven.dao.TransactionDao;
import com.sti.maven.dao.repository.TransactionRepository;
import com.sti.maven.model.Account;
import com.sti.maven.model.Transaction;

public class TransactionImpl extends BaseImpl implements TransactionDao {

	@Autowired
	private TransactionRepository repository ;
	@Override
	public Transaction getById(Integer id) throws Exception {
		
		return repository.findByIdTransaction(id);
	}

	@Override
	public Transaction save(Transaction transaction) throws Exception {
	
		return repository.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) throws Exception {
		repository.delete(transaction);
		
	}

	@Override
	public List<Transaction> getList() throws Exception {
		
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = critB.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		TypedQuery<Transaction> q = em.createQuery(query);
		
		return q.getResultList();
	}

	@Override
	public Integer getSum(String amount, int acc) throws Exception {
		
		return repository.getSumAmount(amount, acc);
	}

	@Override
	public Transaction save(Transaction transaction, String id) throws Exception {
		
		return repository.save(transaction);
	}

}
