package com.javaweb.service.admin;

import java.util.List;
import java.util.Optional;

import com.javaweb.entities.Customer;

public interface CustomerService {

	boolean checkLogin(String name, String password);

	void deleteAll();

	void deleteAll(List<Customer> entities);

	void delete(Customer entity);

	void deleteById(Integer id);

	long count();

	List<Customer> findAllById(List<Integer> ids);

	List<Customer> findAll();

	boolean existsById(Integer id);

	Optional<Customer> findByName(String name);

	Optional<Customer> findByEmail(String email);

	Optional<Customer> findById(Integer id);

	List<Customer> saveAll(List<Customer> entities);

	Customer save(Customer entity);

	

	

}
