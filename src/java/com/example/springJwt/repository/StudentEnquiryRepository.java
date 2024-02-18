package com.example.springJwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springJwt.model.StatusEnum;
import com.example.springJwt.model.Students;

public interface StudentEnquiryRepository extends JpaRepository<Students, Long> {

	boolean existsByName(String name);

	boolean existsByEmail(String email);
	
	  List<Students> findByStatus(StatusEnum status); // Query method to get students by status
	    
}