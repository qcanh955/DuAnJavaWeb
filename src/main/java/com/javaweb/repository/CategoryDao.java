package com.javaweb.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.entities.Category;
import com.javaweb.entities.MapperCategory;

@Repository
public class CategoryDao extends BaseDao {

	public List<Category> GetDataCategorys(){
		List<Category> list = new ArrayList<Category>();
		String sql ="SELECT * FROM category";
		list = _jdbcTemplate.query(sql, new MapperCategory());
		return list;
	}

	
}
