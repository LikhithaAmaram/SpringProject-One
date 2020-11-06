package com.capgemini.cab;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.cab.repository.DriverRepository;
import com.capgemini.cab.service.DriverServiceImpl;

//Annotation that can be specified on a test class that runs Spring Boot based tests
@SpringBootTest
public class DriverResponseTest {

	@Autowired
	DriverServiceImpl driverServiceImpl;
	
	/*Annotation that can be used to add mocks to a Spring 
	*mock is an object which tests the dummy data that we pass
	*/
	@MockBean
	DriverRepository driverRepository;
	
	// used to signal that the annotated method is a test method
	@Test
	void testBooking1()
	{
		//The Mockito library enables mock creation, verification.
		Mockito.when(driverRepository.existsByResponse("Login Successfull!!!")).thenReturn(true);
	}
	
	// used to signal that the annotated method is a test method
	@Test
	void testBooking2()
	{
		//The Mockito library enables mock creation, verification.
		Mockito.when(driverRepository.existsByResponse("Success")).thenReturn(false);
	}
	
	// used to signal that the annotated method is a test method
	@Test
	void testBooking3()
	{
		//The Mockito library enables mock creation, verification.
		Mockito.when(driverRepository.existsByResponse("Success Login")).thenReturn(false);
	}
	
	// used to signal that the annotated method is a test method
	@Test
	void testBooking4()
	{
		//The Mockito library enables mock creation, verification.
		Mockito.when(driverRepository.existsByResponse("Login Successfull")).thenReturn(false);
	}
}
