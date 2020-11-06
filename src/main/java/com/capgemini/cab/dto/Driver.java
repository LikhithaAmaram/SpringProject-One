package com.capgemini.cab.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

//specifies that the class is an entity and is mapped to a database table
@Entity
//name of DB table used for mapping
@Table(name="driver")
public class Driver 
{
	@Id
	//primary key of cab entity
	@NotEmpty(message = "Driver Id is required")
	private String driverid;
	
	// the required attribute should not be empty
	@NotEmpty(message = "First name is required")
	// validating using regex
	@Pattern(regexp="^[a-zA-Z]{3,}$")
	private String driverfirstname;
	
	// the required attribute should not be empty
	@NotEmpty(message = "Last name is required")
	// validating using regex
	@Pattern(regexp="^[a-zA-Z]{3,}$")
	private String driverlastname;
	
	@Column(unique=true)
	// the required attribute should not be empty
	@NotEmpty(message = "Phone number is required")
	// validating using regex
	@Pattern(regexp="^[6-9]{1}[0-9]{9}$",message="Mobile number is invalid")
	private String contact;
	
	@Column(unique=true)
	// the required attribute should not be empty
	@NotEmpty(message = "Cab number is required")
	// validating using regex
	@Pattern(regexp="^[A-Z]{2}\\s[0-9]{2}\\s[A-Z]{2}\\s[0-9]{4}$",message="Cab number is invalid")
	private String cabnumber;
	
	// the required attribute should not be empty
	@NotEmpty(message = "Username  is required")
	private String username;
	
	// the required attribute should not be empty
	@NotEmpty(message = "Password is required")
	private String password;

	@OneToOne(fetch = FetchType.LAZY) //only source object is fetched. by default=eager
	@JoinColumn(name="driverid")
	private Customer rcustomer;

	public String getResponse() {
		return response;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getDriverfirstname() {
		return driverfirstname;
	}
	public void setDriverfirstname(String driverfirstname) {
		this.driverfirstname = driverfirstname;
	}
	public String getDriverlastname() {
		return driverlastname;
	}
	public void setDriverlastname(String driverlastname) {
		this.driverlastname = driverlastname;
	}
	public String getCabnumber() {
		return cabnumber;
	}
	public void setCabnumber(String cabnumber) {
		this.cabnumber = cabnumber;
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
	
	public String response="Login Successfull!!!";
}
