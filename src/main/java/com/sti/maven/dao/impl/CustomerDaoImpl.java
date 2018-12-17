package com.sti.maven.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.compile.CriteriaQueryTypeQueryAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import com.sti.maven.dao.CustomerDao;
import com.sti.maven.dao.repository.CustomerRepository;
import com.sti.maven.model.Customer;

public class CustomerDaoImpl extends BaseImpl implements CustomerDao {

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public Customer getById(Integer id) throws Exception {
		
		return repository.findOne(id) ;
	}

	@Override
	public Customer save(Customer customer) throws Exception {
		
		return repository.save(customer) ;
	}

	@Override
	public void delete(Customer customer) throws Exception {
		
		repository.delete(customer);
		
	}

	@Override
	public List<Customer> getList() throws Exception {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = critB.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(root);
		
		TypedQuery<Customer> q = em.createQuery(query);
		
		return q.getResultList() ;
	}

}
