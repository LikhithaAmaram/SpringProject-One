package com.capgemini.cab.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

// specifies that the class is an entity and is mapped to a database table
@Entity
// name of DB table used for mapping
@Table(name="customer")
public class Customer 
{
	@Id
	//primary key of cab entity
	private String customerid;
	
	// the required attribute should not be empty
	@NotEmpty(message = "First name is required")
	// validating using regex
	@Pattern(regexp="^[a-zA-Z]{3,}$")
	private String firstname;
	
	// the required attribute should not be empty
	@NotEmpty(message = "Last name is required")
	// validating using regex
	@Pattern(regexp="^[a-zA-Z]{3,}$")
	private String lastname;
	
    private char gender;
	
	@Column(unique=true)
	// the required attribute should not be empty
	@NotEmpty(message = "Phone number is required")
	// validating using regex
	@Pattern(regexp="^[6-9]{1}[0-9]{9}$",message="Mobile number is invalid")
	private String contact;
    
	@Column(unique=true)
	// the required attribute should not be empty
	@NotEmpty(message = "Email is required")
	// validating using regex
	@Pattern(regexp="^[a-zA-Z]{1}[a-zA-Z0-9]{2,}@[a-z]{5}.com$",message="Email address is invalid")
	private String email;
	private Integer driver_id;
	private String username;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(Integer driver_id) {
		this.driver_id = driver_id;
	}

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "rcustomer")
	private Driver driver;

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
}
