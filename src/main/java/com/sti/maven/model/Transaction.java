package com.sti.maven.model;
import javax.persistence.*;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name ="Id_transaction")
	private int idTransaction;
	@Column(name ="Type")
	private String type;
	@Column(name = "Amount")
	private String amount;
	@Column(name = "Amount_sign")
	private String amountSign;
	@ManyToOne
	@JoinColumn(name="accountNumber")
	private Account accountNumber;
	
	public Transaction() {}
	
	public Transaction(int idTransaction, String type, String amount, String amountSign, Account accountNumber) {

		this.idTransaction = idTransaction;
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
