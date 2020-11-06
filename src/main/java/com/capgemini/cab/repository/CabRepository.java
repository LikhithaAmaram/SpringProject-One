package com.capgemini.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.cab.dto.Cab;

public interface CabRepository extends JpaRepository<Cab, String>{

}
