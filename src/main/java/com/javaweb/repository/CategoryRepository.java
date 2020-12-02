package com.javaweb.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaweb.entities.Category;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query
	List<Category> findByTheloaiContainingIgnoreCase(String theloai);
	
	@Query
	List<Category> findBytheloaiStartingWith(String theloai);
	
	@Query(value = "select * from category order by id", nativeQuery = true)
	List<Category> findAllCategory();
}
