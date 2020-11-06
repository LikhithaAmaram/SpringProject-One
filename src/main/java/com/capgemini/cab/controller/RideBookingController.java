package com.capgemini.cab.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.cab.service.RideBookingServiceImp;
import com.capgemini.cab.dto.RideBooking;
import com.capgemini.cab.exception.RideBookingException;


@RestController
/*
 * @RestController is a special controller used in RESTFul web services and the equivalent of @Controller + @ResponseBody
 * @Controller is a common annotation that is used to mark a class as Spring MVC Controller
 * @ResponseBody will give us the response from server in JSON body
 */
@RequestMapping("/rideBooking") //maps HTTP request with /rideBooking path to a controller method
public class RideBookingController {
	
	@Autowired
	/*
	 * wiring one object into another object
	 * marks a constructor, field, or setter method to be autowired by Spring dependency injection
	 */	
	private RideBookingServiceImp rideBookingServiceImpl;
	
	//http://localhost:9092/api/rideBooking/details
	//{"bookingid":523, "source":"chennai", "destination":"mumbai", "bookingdate":"2020-04-12", "cid":"1204"}
	
	/*
	 * Author: B.Godha Devi
	 * Method: rideBooking- this method is implemented when customer want to book a ride
	 * Return type: Returns booking response to the customer
	 * Parameter: we'll pass validated rideBooking object which contains all the necessary attributes.
	 * Description: In this method, we'll validate whether the user can book  a cab or not
	 */
	
	@RequestMapping(method=RequestMethod.POST, value="/details")
	public String rideBooking(@RequestBody RideBooking rideBooking)
	{
		return rideBookingServiceImpl.bookRide(rideBooking);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	/*
	 * Marks a method or exception class with the status code and reason that should be returned. 
       The status code is applied to the HTTP response when the handler method is invoked.
	 */
	@ExceptionHandler(RideBookingException.class)
	/*
	 * handling exceptions in specific handler classes and/or handler methods  i.e in CustomerException class
	 */
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		// Exception is handled and passes the MethodArgumentNotValidException
		/*
		 * getBindingResult()- Returns the results of the failed validation.
		 * getFieldErrors()- Get all errors associated with a field.
		 * getDefaultMessage()- Return the default message to be used to resolve this message, by default null.
		 * forEach()- Performs the given action for each element of the Iterable until all elements have been processed 
		 *            or the action throws an exception 
		 */
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach(error -> 
	        errors.put(error.getField(), error.getDefaultMessage()));
	     
	    return errors;
	}
}
