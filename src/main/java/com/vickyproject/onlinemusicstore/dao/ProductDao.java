package com.vickyproject.onlinemusicstore.dao;

import java.util.List;

import com.vickyproject.onlinemusicstore.entity.Product;

public interface ProductDao {
public List<Product> findAll();
	
	public Product findById(int theId);
	
	public void save(Product theProduct);
	
	public void deleteById(int theId);
}
