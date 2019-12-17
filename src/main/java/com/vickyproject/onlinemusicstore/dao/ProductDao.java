package com.vickyproject.onlinemusicstore.dao;

import java.util.ArrayList;
import java.util.List;

import com.vickyproject.onlinemusicstore.entity.Product;

public class ProductDao {
	public List<Product> getProductList()
	{
		// dummy product
		Product prod1 = new Product();
		
		prod1.setProductName("Guitar1");
		prod1.setProductCategory("Instrument");
		prod1.setProductDescription("This is a fender strat guitar!");
		prod1.setProductPrice(1200);
        prod1.setProductCondition("new");
        prod1.setProductStatus("Active");
        prod1.setUnitInStock(11);
        prod1.setProductManufacturer("Fender");
        
        List<Product> prodList = new ArrayList<Product>();
        
        prodList.add(prod1);
        
        return prodList;
	}

}