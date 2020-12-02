package com.javaweb.service.admin;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.javaweb.entities.Category;
import com.javaweb.entities.Product;

@Service
@Transactional
public interface ProductService {

	List<Product> findAllByTL(List<Long> categoryid);

	List<Product> findAllByName(String namesp);

	List<Product> findAllByNameAndTL(String namesp, List<Long> categoryid);

	void changeProductPrice(Long id, Double price);

	

	List<Category> findAllCategory();

	void deleteAll();

	void deleteById(Long id);

	long count();

	Product getOne(Long id);

	Optional<Product> findById(Long id);

	List<Product> findAll();

	Product save(Product entity);

	List<Product> findByNameContainingIgnoreCase(String nameprd);

}
