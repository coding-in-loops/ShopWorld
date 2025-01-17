package com.shopworld.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.shopworld.entities.Category;


@Service
public interface CategoryService {
	
	public Category saveCategory(Category category);
	
	public Boolean existCategory(String name);
	
	public List<Category> getAllCategories();
	
	public Boolean deleteCategory(int id);
	
	public Category getCategoryById(int id);
	
	public List<Category> getAllActiveCategory();
	
	public Page<Category> getAllCategoryPagination(Integer pageNo,Integer pageSize);
}
