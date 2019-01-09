package com.sti.maven.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sti.maven.model.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

}
