package com.capgemini.cab.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.aspectj.weaver.bcel.ExceptionRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.capgemini.cab.dto.Cab;
import com.capgemini.cab.dto.Customer;
import com.capgemini.cab.dto.Driver;
import com.capgemini.cab.dto.RideBooking;
import com.capgemini.cab.exception.CustomerException;
import com.capgemini.cab.service.CustomerService;
import com.capgemini.cab.service.CustomerServiceImpl;


@RestController
/*
 * @RestController is a special controller used in RESTFul web services and the equivalent of @Controller + @ResponseBody
 * @Controller is a common annotation that is used to mark a class as Spring MVC Controller
 * @ResponseBody will give us the response from server in JSON body
 */
@RequestMapping("/customer") //maps HTTP request with /customer path to a controller method
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	/*
	 * wiring one object into another object
	 * marks a constructor, field, or setter method to be autowired by Spring dependency injection
	 */
	private CustomerServiceImpl customerServiceImpl;
	
	//http://localhost:9092/api/customer/login
		//{"username":"godhadevi","password":"Bandari@123"}
	
	/*
	 * Author: Bhavita
	 * Method: customerLogin()- this method is implemented when customer wants to login
	 * Return type: Returns string which gives login response
	 * Parameter: we'll pass validated customer object which contains all the necessary attributes.
	 * Description: In this method, we'll validate the customer login credentials
	 */
	
		@RequestMapping(method=RequestMethod.POST,value="/login")
		// maps HTTP request with /login path to customerLogin method
		
		public String customerLogin(@RequestBody Customer customer)
		{
			// By using @RequestBody, all customer data will be passed to the server through JSON body
		String result=customerServiceImpl.customerLogin(customer);
		return result;
		}
	
	
	//http://localhost:9092/api/customer/add
//{"customerid":"543","firstname":"Godha","lastname":"Bandari","gender":"f","contact":"7095531873","email":"godha@gmail.com","username":"godhadevi","password":"Bandari@123","driver_id":507}
	
		
		/*
		 * Author: Bhavita
		 * Method: addCandidate- this method is implemented when we want to add a customer details
		 * Return type: Returns ResponseEntity which includes customer details
		 * Parameter: we'll pass validated customer object which contains all the necessary attributes.
		 * Description: In this method, we'll add customer details by calling customer service
		 */
		
		
	@RequestMapping(method=RequestMethod.POST, value="/add")
	public ResponseEntity<String> addCandidate(@RequestBody @Valid Customer customer)
	{
		// @Valid performs all the required validations on customer data
		try
		{
		String result=customerServiceImpl.addCustomer(customer);
		if(result.isEmpty())
		{
			logger.error("No Records Found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info("Driver Login Details Found "+result);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	catch (Exception e) 
	{
		logger.error("Driver Login Details Not Found: ");
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	/*
	 * Marks a method or exception class with the status code and reason that should be returned. 
       The status code is applied to the HTTP response when the handlermethod is invoked.
	 */
	@ExceptionHandler(CustomerException.class)
	/*
	 * handling exceptions in specific handler classes and/or handler methods  i.e in CustomerException class
	 */
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		// Exception is handled and passes the MethodArgumentNotValidException
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach(error -> 
	        errors.put(error.getField(), error.getDefaultMessage()));
	     
	    return errors;
	}
	//http://localhost:9092/api/customer/history
	
	/*
	 * Author: Bhavita
	 * Method: getHistory- this method is implemented when customer wants to get ride booking history details
	 * Return type: Returns ArrayList which includes ride booking history details
	 * Parameter: we'll pass rideBooking object which contains all the necessary attributes.
	 * Description: In this method, we'll get ride booking history details by calling customer service
	 */
	
	@RequestMapping(method=RequestMethod.GET,value="/history")
	public ArrayList<RideBooking> getHistory(@RequestBody RideBooking rideBooking)
	{
	ArrayList<RideBooking> result=customerServiceImpl.bookingHistoryCustomers();
	return result;
	}
	
	//http://localhost:9092/api/customer/finalfare
	
	/*
	 * Author: Rishitha
	 * Method: getFinalFare- this method is implemented when customer wants to get final fare after the ride
	 * Return type: Returns a float value which is the final fare
	 * Description: In this method, we'll get final fare by calling customer service
	 */
	
	@RequestMapping(method=RequestMethod.GET,value="/finalfare")
	public float getFinalFare()
	{
		float finalFare=customerServiceImpl.finalFare();
		return finalFare;
	}
	
	//http://localhost:9092/api/customer/cabDetails
	
	/*
	 * Author: Rishitha
	 * Method: getDriverDetails- this method is implemented when customer wants to get cab details
	 * Return type: Returns ArrayList which includes cab details
	 * Parameter: we'll pass cab object which contains all the necessary attributes.
	 * Description: In this method, we'll get cab details by calling customer service
	 */
	
	@RequestMapping(method=RequestMethod.GET,value="/cabDetails")
	public ArrayList<Cab> getCabDetails(@RequestBody Cab cab)
	{
		ArrayList<Cab> result=customerServiceImpl.cabDetails();
		return result;
	}
	
	//http://localhost:9092/api/customer/driverDetails
	
	/*
	 * Author: Rishitha
	 * Method: getCabDetails- this method is implemented when customer wants to get cab details
	 * Return type: Returns ArrayList which includes Driver details
	 * Parameter: we'll pass driver object which contains all the necessary attributes.
	 * Description: In this method, we'll get driver details by calling customer service
	 */
	
	@RequestMapping(method=RequestMethod.GET,value="/driverDetails")
	public ArrayList<Driver> getDriverDetails(@RequestBody Driver driver)
	{
		ArrayList<Driver> result=customerServiceImpl.driverDetails();
		return result;
	}
	
	
	public CustomerService getCustomerServiceImpl() {
		return customerServiceImpl;
	}

	public void setCustomerService(CustomerServiceImpl customerServiceImpl) {
		this.customerServiceImpl = customerServiceImpl;
	}
}
