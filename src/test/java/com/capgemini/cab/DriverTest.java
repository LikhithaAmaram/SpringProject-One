package com.capgemini.cab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.cab.dto.Driver;
import com.capgemini.cab.repository.DriverRepository;
import com.capgemini.cab.service.DriverServiceImpl;

//Annotation that can be specified on a test class that runs Spring Boot based tests
@SpringBootTest
public class DriverTest {
	
	/*Annotation that can be used to add mocks to a Spring 
	*mock is an object which tests the dummy data that we pass
	*/
	@Autowired
	DriverServiceImpl driverServiceImpl;
	
	@MockBean
	DriverRepository driverRepository;
	
	// used to signal that the annotated method is a test method
	@Test
	public void loginTest1()
	{
		Driver driver=new Driver();
		driver.setUsername("ajay"); 
		driver.setPassword("Ajay@123"); 
		//The Mockito library enables mock creation, verification.
		Mockito.when(driverRepository.existsByUsername("ajay")).thenReturn(true);
		Mockito.when(driverRepository.existsByUsername("Ajay@1")).thenReturn(false);
		assertEquals("Invalid", driverServiceImpl.driverLogin(driver));
	}
	
	// used to signal that the annotated method is a test method
	@Test
	public void loginTest2()
	{
		Driver driver=new Driver();
		driver.setUsername("ajay"); 
		driver.setPassword("Ajay@123"); 
		//The Mockito library enables mock creation, verification.
		Mockito.when(driverRepository.existsByUsername("aj")).thenReturn(false);
		Mockito.when(driverRepository.existsByUsername("Ajay@123")).thenReturn(true);
		assertEquals("Invalid", driverServiceImpl.driverLogin(driver));
	}
	
	// used to signal that the annotated method is a test method
	@Test
	public void loginTest3()
	{
		Driver driver=new Driver();
		driver.setUsername("ajay"); 
		driver.setPassword("Ajay@123"); 
		//The Mockito library enables mock creation, verification.
		Mockito.when(driverRepository.existsByUsername("aj")).thenReturn(false);
		Mockito.when(driverRepository.existsByUsername("Ajay@1")).thenReturn(false);
		assertEquals("Invalid", driverServiceImpl.driverLogin(driver));
	}

}
