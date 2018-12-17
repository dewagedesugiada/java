package com.sti.maven.model;

import java.util.Date;
import javax.persistence.*;

import org.springframework.boot.autoconfigure.web.ResourceProperties.Strategy;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int accountNumber;
	@Column
	private Date openDate;
	@Column
	private String balance;
	
	@ManyToOne
	@JoinColumn(name="customer_number")
	private Customer customerNumber;
	
	private Account() {}

	public Account(int accountNumber, Date openDate, String balance, Customer customerNumber) {
		super();
		this.accountNumber = accountNumber;
		this.openDate = openDate;
		this.balance = balance;
		this.customerNumber = customerNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public Customer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Customer customerNumber) {
		this.customerNumber = customerNumber;
	}
	

	
}
