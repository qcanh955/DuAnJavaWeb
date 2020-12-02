package com.javaweb.service.admin;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entities.Category;
import com.javaweb.entities.Product;
import com.javaweb.repository.CategoryRepository;
import com.javaweb.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Product save(Product entity) {
		return productRepository.save(entity);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Product getOne(Long id) {
		return productRepository.getOne(id);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	@Override
	public List<Category> findAllCategory(){
		return (List<Category>)categoryRepository.findAll();
	}

	@Override
	public List<Product> findByNameContainingIgnoreCase(String nameprd) {
		return productRepository.findByNameContainingIgnoreCase(nameprd);
	}

	@Override
	public void changeProductPrice(Long id, Double price) {
		Product p = new Product();
    	p = productRepository.findById(id).get();
    	p.setPrice(price);
    	productRepository.save(p);
		
	}

	@Override
	public List<Product> findAllByNameAndTL(String nameprd, List<Long> categoryid) {
		return productRepository.findAllByNameAndTL(nameprd, categoryid);
	}

	@Override
	public List<Product> findAllByName(String nameprd) {
		return productRepository.findAllByName(nameprd);
	}

	@Override
	public List<Product> findAllByTL(List<Long> categoryid) {
		return productRepository.findAllByTL(categoryid);
	}
	

}