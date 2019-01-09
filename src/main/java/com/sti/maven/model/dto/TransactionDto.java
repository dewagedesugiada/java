package com.sti.maven.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sti.maven.model.Account;

public class TransactionDto {
	private int idTransaction;
	private String type;
	private String amount;
	private String amountSign;

	@JsonProperty("account")
	private Account accountNumber;

	public TransactionDto() {}

	public TransactionDto(String type, String amount, String amountSign, Account accountNumber) {
		this.type = type;
		this.amount = amount;
		this.amountSign = amountSign;
		this.accountNumber = accountNumber;
	}

	public int getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountSign() {
		return amountSign;
	}

	public void setAmountSign(String amountSign) {
		this.amountSign = amountSign;
	}

	public Account getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Account accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	
	
}
