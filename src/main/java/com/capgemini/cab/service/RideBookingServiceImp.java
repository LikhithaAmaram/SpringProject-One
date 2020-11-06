package com.capgemini.cab.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.cab.dto.Customer;
import com.capgemini.cab.dto.RideBooking;

import com.capgemini.cab.repository.RideBookingRepository;


@Service //class that provides business services.
public class RideBookingServiceImp implements RideBookingService{
	
	@PersistenceContext
	// to indicate that the EntityManager must be automatically injected
	EntityManager entityManager;
	@Autowired
	/* wiring one object into other object.enables you to inject the object dependency implicitly.
	 * ways- (default,byType,byName,constructor)
	 */
	private RideBookingRepository rideBookingRepository;
	//immutable date-time object that represents a date-time,often viewed as year-month-day-hour-minute-second.
	LocalDateTime localDateTime1 = LocalDateTime.now(); 
    LocalDateTime localDateTime2 = LocalDateTime.of(2020, 04, 12, 10, 10, 50);
    LocalDateTime localDateTime3 = LocalDateTime.of(2020, 04, 12, 10, 20, 50);
    
    /*
	 * Author: Godha Devi
	 * Method: bookRide- this method is implemented when customer wants to book a cab
	 * Return type: Returns a string whether cab is booked or not
	 * Parameter: we'll pass rideBooking object which contains all the necessary attributes to book a ride 
	 * Description: In this method, we'll validate four conditions to give response to user
	 */
    public String bookRide(RideBooking rideBooking)
    {
    	String id="";
    	/*
    	 * Native query- refers to actual sql queries
    	 * Named query- define your query by giving it a name.
    	 * Typed query- gives you an option to mention the type of entity when you create a query
    	 */
		Query query=entityManager.createQuery("select c from Customer c");
		List<Customer> list=query.getResultList(); // when we don't know how many records Customer entity contains else getSingleResult()
		for(Customer v:list)
		{
			id=v.getCustomerid();
			if(id.equals(rideBooking.getCid()))
			{
				if (!((localDateTime1.compareTo(localDateTime2) >= 0) && (localDateTime1.compareTo(localDateTime3) <= 0) ))
				{
					/* query is an interface in which we request data from the database
					 * createNamedQuery(name of the query that will be used with the createNamedQuery method)
					 * createNativeQuery(pure SQL query)
					 * createStoredProcedureQuery(procedureName, resultClasses),
					 * createQuery(Query object using the HQL syntax.),
					 * createSqlQuery(Query object using the native SQL syntax)
					 */
				Query query1=entityManager.createQuery("select r.cid from RideBooking r");
					List<Object> list1=query1.getResultList();
					for(Object obj:list1)
					{
						String cList=(String)obj;
						if(id.equals(cList))
						{
							return "Already Booked!!!";
						}
					}
					rideBookingRepository.save(rideBooking);
					return "Booked successfully";
			    }
			    else
			    {
			         return "Booking Cancelled";
			    }
			}
		}
		return "Sorry you can't Book a Cab";
}
}
