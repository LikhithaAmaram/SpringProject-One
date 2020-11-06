package com.capgemini.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.cab.dto.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer>{
	
	public boolean existsByPassword(String password);

	public boolean existsByUsername(String username);
	
	public boolean existsByResponse(String response);

}
