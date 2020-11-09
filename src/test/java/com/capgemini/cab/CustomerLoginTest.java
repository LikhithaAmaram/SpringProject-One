package com.capgemini.cab;

 

import static org.junit.jupiter.api.Assertions.assertEquals;

 

import org.junit.After;
import org.junit.Before;
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
public class CustomerLoginTest {
    
    //Annotation that can be used to add mocks to a Spring 
    //mock is an object which tests the dummy data that we pass
    
    @Autowired
    CustomerServiceImpl customerServiceImpl;
    
    @MockBean
    CustomerRepository customerRepository;
    
     Customer customer=new Customer();
     //causes that method to be run before the test
    @Before
    public void Beforetest1() {
        customer.setUsername("Likhitha");
        customer.setPassword("Likhitha@123");
    }
    // used to signal that the annotated method is a test method
    @Test
    public void loginTest1()
    {                                                                                                                
        /*
         * Customer customer=new Customer(); customer.setUsername("likhitha");
         * customer.setPassword("likhitha@123");
         */
        //The Mockito library enables mock creation, verification.
        Mockito.when(customerRepository.existsByUsername("Likhitha")).thenReturn(true);
        Mockito.when(customerRepository.existsByUsername("Likhitha@1")).thenReturn(false);
        assertEquals("Invalid", customerServiceImpl.customerLogin(customer));
    }
    @Before
    public void Beforetest2() {
        customer.setUsername("Likhitha");
        customer.setPassword("Likhitha@123");
    }
    
    // used to signal that the annotated method is a test method
    @Test
    public void loginTest2()
    {
        //The Mockito library enables mock creation, verification.
        Mockito.when(customerRepository.existsByUsername("Likhithareddy")).thenReturn(false);
        Mockito.when(customerRepository.existsByUsername("Likhitha@123")).thenReturn(true);
        assertEquals("Invalid", customerServiceImpl.customerLogin(customer));
    }
    
    // used to signal that the annotated method is a test method
    @Before
    public void Beforetest3() {
        customer.setUsername("Likhitha");
        customer.setPassword("Likhitha@123");
    }
    @Test
    public void loginTest3()
    {
        //The Mockito library enables mock creation, verification.
        Mockito.when(customerRepository.existsByUsername("Likhitha")).thenReturn(false);
        Mockito.when(customerRepository.existsByUsername("Reddy123")).thenReturn(false);
        assertEquals("Invalid", customerServiceImpl.customerLogin(customer));
    }
     @After 
     public void afterTest3() {
          System.gc();
     }
     

 

}