package com.sti.maven.model;

import java.sql.Date;  
import javax.persistence.*;

@Entity
public class Customer {
	@Id

	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerNumber ;
	
	@Column(name="First_name")
	private String firstName ;
	
	@Column(name="Last_name")
	private String lastName ;
	
	@Column(name="Birth_date")
	private Date birthDate;
	
	@Column(name="Username")
	private String username;
	
	@Column(name="Password")
	private String password ;
	
	@Column(name="Phone_type")
	private String phonetype ;
	
	@Column(name="Phone_number")
	private String phoneNumber ;
	
	
	
	public Customer() {}



	public Customer(String firstName, String lastName, Date birthDate, String username,
			String password, String phonetype, String phoneNumber) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.username = username;
		this.password = password;
		this.phonetype = phonetype;
		this.phoneNumber = phoneNumber;
	}



	public int getCustomerNumber() {
		return customerNumber;
	}



	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Date getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getPhonetype() {
		return phonetype;
	}



	public void setPhonetype(String phonetype) {
		this.phonetype = phonetype;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}//