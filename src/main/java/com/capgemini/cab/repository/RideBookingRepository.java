package com.capgemini.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.cab.dto.RideBooking;

public interface RideBookingRepository extends  JpaRepository<RideBooking, Integer>{

}
