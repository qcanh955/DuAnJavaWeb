package com.javaweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.javaweb.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	@Query
	Optional<Customer> findByEmail(String email);
	
	@Query(value = "select * from customer where fullname = ?",nativeQuery = true)
	Optional<Customer> findByName(String name);
}
