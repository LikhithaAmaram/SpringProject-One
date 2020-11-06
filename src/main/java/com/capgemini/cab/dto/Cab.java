package com.capgemini.cab.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity  //specifies that the class is an entity and is mapped to a database table
@Table(name="cab") // name of DB table used for mapping
public class Cab {
	
	@Id // says that cabnumber is a primary key of cab entity
	@Pattern(regexp="^[A-Z]{2}\\s[0-9]{2}\\s[A-Z]{2}\\s[0-9]{4}$",message="Cab number is invalid") // regex validation
	@NotEmpty(message = "Cab Number is required") //java specific request
	private String cabnumber;
	
	@Column(unique=true)
	@NotEmpty(message = "Cab Model is required")
    private String cabmodel;
	
	@Column(unique=true)
	@NotEmpty(message = "Cab Size is required")
    private int cabsize;
	
	public String getCabnumber() {
		return cabnumber;
	}
	public void setCabnumber(String cabnumber) {
		this.cabnumber = cabnumber;
	}
	public String getCabmodel() {
		return cabmodel;
	}
	public void setCabmodel(String cabmodel) {
		this.cabmodel = cabmodel;
	}
	public int getCabsize() {
		return cabsize;
	}
	public void setCabsize(int cabsize) {
		this.cabsize = cabsize;
	}
    
	
}
