package com.capgemini.cab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.cab.service.CustomerServiceImpl;

//Annotation that can be specified on a test class that runs Spring Boot based tests
@SpringBootTest
public class FinalFareTest {
	
	
	@Autowired
	CustomerServiceImpl customerServiceImpl;
	
	// used to signal that the annotated method is a test method
	 @Test
	    void testFinalFare()
	    {
	        assertEquals(17000.0, customerServiceImpl.finalFare(), "Final Fare is not correct" );
	    }
	
	
	
}
