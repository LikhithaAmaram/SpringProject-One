package com.capgemini.cab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.cab.dto.Customer;
import com.capgemini.cab.repository.CustomerRepository;
import com.capgemini.cab.service.CustomerServiceImpl;

//Annotation that can be specified on a test class that runs Spring Boot based tests
@SpringBootTest
public class CustomerTest {
	
	//Annotation that can be used to add mocks to a Spring 
	//mock is an object which tests the dummy data that we pass
	@Autowired
	CustomerServiceImpl customerServiceImpl;
	
	@MockBean
	CustomerRepository customerRepository;
	
	// used to signal that the annotated method is a test method
	@Test
	public void loginTest1()
	{
		Customer customer=new Customer();
		customer.setUsername("godhadevi");
		customer.setPassword("godhai@123");
		//The Mockito library enables mock creation, verification.
		Mockito.when(customerRepository.existsByUsername("godhadevi")).thenReturn(true);
		Mockito.when(customerRepository.existsByUsername("godhai@123")).thenReturn(false);
		assertEquals("Invalid", customerServiceImpl.customerLogin(customer));
	}
	
	// used to signal that the annotated method is a test method
	@Test
	public void loginTest2()
	{
		Customer customer=new Customer();
		customer.setUsername("godha");
		customer.setPassword("Bandari@123");
		//The Mockito library enables mock creation, verification.
		Mockito.when(customerRepository.existsByUsername("godha")).thenReturn(false);
		Mockito.when(customerRepository.existsByUsername("Bandari@123")).thenReturn(true);
		assertEquals("Invalid", customerServiceImpl.customerLogin(customer));
	}
	
	// used to signal that the annotated method is a test method
	@Test
	public void loginTest3()
	{
		Customer customer=new Customer();
		customer.setUsername("godha");
		customer.setPassword("Bandari@1");
		//The Mockito library enables mock creation, verification.
		Mockito.when(customerRepository.existsByUsername("godha")).thenReturn(false);
		Mockito.when(customerRepository.existsByUsername("Bandari@1")).thenReturn(false);
		assertEquals("Invalid", customerServiceImpl.customerLogin(customer));
	}

}
