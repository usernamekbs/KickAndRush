package com.kick.repository.category;

import java.util.List;

import com.kick.entity.Category;

public interface CustomCategoryRepository {
	
	List<Category> findByCategory();
}
