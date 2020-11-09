package com.capgemini.cab;

 

import static org.junit.jupiter.api.Assertions.assertEquals;

 

import org.junit.After;
import org.junit.Before;
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
public class DriverLoginTest {
    
    /*Annotation that can be used to add mocks to a Spring 
    *mock is an object which tests the dummy data that we pass
    */
    @Autowired
    DriverServiceImpl driverServiceImpl;
    
    @MockBean
    DriverRepository driverRepository;
    Driver driver=new Driver();
    @Before             //@Before causes that method to be run before the Test method
    public void Beforetest1() {
        driver.setUsername("ajay"); 
        driver.setPassword("Ajay@123"); 
    }
    
    // used to signal that the annotated method is a test method
    @Test
    public void loginTest1()
    {
        //The Mockito library enables mock creation, verification.
        Mockito.when(driverRepository.existsByUsername("ajay")).thenReturn(true);
        Mockito.when(driverRepository.existsByPassword("Ajay@1")).thenReturn(false);
        assertEquals("Invalid", driverServiceImpl.driverLogin(driver));
    }
    @Before //@Before causes that method to be run before the Test method
public void BeforeTest2() {
        driver.setUsername("ajay"); 
        driver.setPassword("Ajay@123"); 
    }
    
    // used to signal that the annotated method is a test method
    @Test
    public void loginTest2()
    { 
        //The Mockito library enables mock creation, verification.
        Mockito.when(driverRepository.existsByUsername("aj")).thenReturn(false);
        Mockito.when(driverRepository.existsByPassword("Ajay@123")).thenReturn(true);
        assertEquals("Invalid", driverServiceImpl.driverLogin(driver));
    }
    
    @Before
    public void Beforetest3() {
        driver.setUsername("ajay"); 
        driver.setPassword("Ajay@123");
    }
    // used to signal that the annotated method is a test method
    @Test
    public void loginTest3()
    {
        //The Mockito library enables mock creation, verification.
        Mockito.when(driverRepository.existsByUsername("aj")).thenReturn(false);
        Mockito.when(driverRepository.existsByPassword("Ajay@1")).thenReturn(false);
        assertEquals("Invalid", driverServiceImpl.driverLogin(driver));
    }
    @After
    public void AfterTest() {
        System.gc(); //unused objects in order to make the memory they currently occupy available for quick reuse
    }
}