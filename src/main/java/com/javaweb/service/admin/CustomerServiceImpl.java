package com.javaweb.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entities.Customer;
import com.javaweb.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer save(Customer entity) {
		return customerRepository.save(entity);
	}

	@Override
	public List<Customer> saveAll(List<Customer> entities) {
		return (List<Customer>)customerRepository.saveAll(entities);
	}

	@Override
	public Optional<Customer> findById(Integer id) {
		return customerRepository.findById(id);
	}
	
	@Override
	public Optional<Customer> findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
	
	@Override
	public Optional<Customer> findByName(String name) {
		return customerRepository.findByName(name);
	}

	@Override
	public boolean existsById(Integer id) {
		return customerRepository.existsById(id);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>)customerRepository.findAll();
	}

	@Override
	public List<Customer> findAllById(List<Integer> ids) {
		return (List<Customer>)customerRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return customerRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		customerRepository.deleteById(id);
	}

	@Override
	public void delete(Customer entity) {
		customerRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<Customer> entities) {
		customerRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		customerRepository.deleteAll();
	}
	
	@Override
	public boolean checkLogin(String name, String password) {
		Optional<Customer> optionalUser = findByEmail(name);
		if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
}
