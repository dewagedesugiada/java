package com.sti.maven.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sti.maven.model.Customer;

public class AccountDto {
	private int accountNumber;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date openDate;
	private String balance;	
	@JsonProperty("customer")
	private Customer customerNumber;
	
	public AccountDto() {}

	public AccountDto(Date openDate, String balance, Customer customerNumber) {
		

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
