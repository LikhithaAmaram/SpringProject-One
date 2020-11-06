package com.capgemini.cab.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.cab.dto.Driver;
import com.capgemini.cab.exception.DriverException;
import com.capgemini.cab.service.DriverServiceImpl;

@RestController
/*
 * @RestController is a special controller used in RESTFul web services and the equivalent of @Controller + @ResponseBody
 * @Controller is a common annotation that is used to mark a class as Spring MVC Controller
 * @ResponseBody will give us the response from server in JSON body
 */
@RequestMapping("/driver") //maps HTTP request with /driver path to a controller method
public class DriverController {
	private static final Logger logger = LoggerFactory.getLogger(DriverController.class);
	@Autowired
	/*
	 * wiring one object into another object
	 * marks a constructor, field, or setter method to be autowired by Spring dependency injection
	 */
	private DriverServiceImpl driverServiceImpl;
	
	        //http://localhost:9092/api/driver/login
			//{"username":"ajay","password":"Ajay@123"}
	
	/*
	 * Author: Likitha Reddy
	 * Method: driverLogin()- this method is implemented when driver wants to login
	 * Return type: Returns string which gives login response
	 * Parameter: we'll pass validated driver object which contains all the necessary attributes.
	 * Description: In this method, we'll validate the driver login credentials
	 */
	
			@RequestMapping(method=RequestMethod.POST,value="/login")
			// maps HTTP request with /login path to driverLogin method
			public ResponseEntity<String> driverLogin(@RequestBody Driver driver)
			{
				// By using @RequestBody, all driver data will be passed to the server through JSON body
				try
				{
					String result=driverServiceImpl.driverLogin(driver);
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
			
			
			//http://localhost:9092/api/driver/response

			/*
			 * Author: Likitha Reddy
			 * Method: driverLogin()- this method is implemented when driver wants to login
			 * Return type: Returns string which gives login response
			 * Parameter: we'll pass validated driver object which contains all the necessary attributes.
			 * Description: In this method, we'll validate the driver login credentials
			 */
			
			@RequestMapping(method=RequestMethod.GET, value="/response")
			// maps HTTP request with /response path to driverResponse method
			public String driverResponse()
			{
				// By using @RequestBody, all driver data will be passed to the server through JSON body
				String result=driverServiceImpl.driverResponse();
				return result;
			}
			@ResponseStatus(HttpStatus.BAD_REQUEST)
			/*
			 * Marks a method or exception class with the status code and reason that should be returned. 
		       The status code is applied to the HTTP response when the handlermethod is invoked.
			 */
			@ExceptionHandler(DriverException.class)
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
}
