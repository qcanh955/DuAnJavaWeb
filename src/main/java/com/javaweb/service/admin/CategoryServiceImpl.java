package com.javaweb.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entities.Category;
import com.javaweb.repository.CategoryRepository;

@Service
public class CategoryServiceImpl  implements CategoryService{
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category save(Category entity) {
		return categoryRepository.save(entity);
	}

	@Override
	public List<Category> saveAll(List<Category> entities) {
		return (List<Category>)categoryRepository.saveAll(entities);
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return categoryRepository.existsById(id);
	}

	@Override
	public List<Category> findAll() {
		return (List<Category>)categoryRepository.findAll();
	}

	@Override
	public List<Category> findAllById(List<Integer> ids) {
		return (List<Category>)categoryRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<Category> entities) {
		categoryRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}
	
	@Override
	public List<Category> findBytheloaiStartingWith(String theloai) {
	// TODO Auto-generated method stub
	return categoryRepository.findBytheloaiStartingWith(theloai);
	}
	
	@Override
	public List<Category> findByTheloaiContainingIgnoreCase(String theloai) {
		return this.categoryRepository.findByTheloaiContainingIgnoreCase(theloai);
	}

	@Override
	public List<Category> findAllCategory() {
		return categoryRepository.findAllCategory();
	}
	
	
}	
