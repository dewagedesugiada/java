package com.sti.maven.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.maven.dao.AccountDao;
import com.sti.maven.dao.repository.AccountRepository;
import com.sti.maven.error.CustomException;
import com.sti.maven.model.Account;
import com.sti.maven.model.Customer;

public class AccountDaoImpl extends BaseImpl implements AccountDao  {
	
	@Autowired
	private AccountRepository repository ;
	
	@Override
	public Account getById(Integer id) throws CustomException {
		
		return repository.findByAccountNumber(id);
	}

	@Override
	public Account save(Account account) throws CustomException {
	
		return repository.save(account) ;
	}

	@Override
	public void delete(Account account) throws CustomException {
		repository.delete(account);
		
	}

	@Override
	public List<Account> getList() throws CustomException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Account> query = critB.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		query.select(root);
		
		TypedQuery<Account> q = em.createQuery(query);
		return q.getResultList();
	}

	@Override
	public Account save(Account account, String id) throws CustomException {
		
		return repository.save(account);
	}

	@Override
	public List<Account> getListByCustomer(Customer customer) throws CustomException {
		
		return repository.findByCustomerNumber(customer);
	}
	

}
