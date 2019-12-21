package com.vickyproject.onlinemusicstore.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vickyproject.onlinemusicstore.dao.ProductDao;
import com.vickyproject.onlinemusicstore.entity.Product;

@Controller
@RequestMapping("/online-music-store/")
public class MainController {
	
	ProductDao dao = new ProductDao();
	
	@RequestMapping("")
	public String homePage()
	{
		return "home";
	}
	
	@RequestMapping("productList")
	public String getProduct(Model theModel)
	{
		List<Product> prodList = dao.getProductList();
		
		theModel.addAttribute("prod1", prodList.get(0));
		
		return "productList";
	}
	
	@RequestMapping("viewProduct")
	public String productDetails(Model theModel)
	{
		return "viewProduct";
	}

}
