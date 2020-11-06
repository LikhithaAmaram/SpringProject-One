package com.capgemini.cab.service;

import java.util.ArrayList;
import com.capgemini.cab.dto.Cab;
import com.capgemini.cab.dto.Customer;
import com.capgemini.cab.dto.Driver;
import com.capgemini.cab.dto.RideBooking;

public interface CustomerService  {
		
	/*
	 * Author: Rishitha
	 * Description: contains abstract method called bookingHistoryCustomers() 
	 */
	
	public ArrayList<RideBooking>  bookingHistoryCustomers();
	
	/*
	 * Author: Rishitha
	 * Description: contains abstract method called finalFare()
	 */
	
	public float finalFare();

	public String customerLogin(Customer customer);
	
	public String addCustomer(Customer customer);
	
	public ArrayList<Cab> cabDetails();
	
	public ArrayList<Driver> driverDetails();
}