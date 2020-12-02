package com.javaweb.service.admin;

import java.util.List;
import java.util.Optional;

import com.javaweb.entities.Category;

public interface CategoryService {

	List<Category> findByTheloaiContainingIgnoreCase(String theloai);

	List<Category> findBytheloaiStartingWith(String theloai);

	void deleteAll();

	void deleteAll(List<Category> entities);

	void delete(Category entity);

	void deleteById(Integer id);

	long count();

	List<Category> findAllById(List<Integer> ids);

	List<Category> findAll();

	boolean existsById(Integer id);

	Optional<Category> findById(Integer id);

	List<Category> saveAll(List<Category> entities);

	Category save(Category entity);

	List<Category> findAllCategory();

	

	

}
