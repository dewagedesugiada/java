package com.sti.maven.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@SuppressWarnings("unused")
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int accountNumber;
	@Column(name="Open_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date openDate;
	@Column(name="Balance")
	private String balance;
	
	@ManyToOne
	@JoinColumn(name="customer_number")
	@JsonProperty("customer")
	private Customer customerNumber;
	

	private Account() {}

	public Account( Date openDate, String balance, Customer customerNumber) {
		
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
