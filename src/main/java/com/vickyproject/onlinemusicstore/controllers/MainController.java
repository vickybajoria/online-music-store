package com.vickyproject.onlinemusicstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vickyproject.onlinemusicstore.dao.ProductDao;
import com.vickyproject.onlinemusicstore.entity.Product;

@Controller
@RequestMapping("/online-music-store/")
public class MainController {
	
	@Autowired
	ProductDao theProductDao;
	
	@RequestMapping("")
	public String homePage()
	{
		return "home";
	}
	
	@RequestMapping("productList")
	public String getProduct(Model theModel)
	{
		List<Product> prodList = theProductDao.findAll();
		
		theModel.addAttribute("prodList", prodList);
		
		return "productList";
	}
	
	@RequestMapping("viewProduct/{pId}")
	public String productDetails(@PathVariable int pId, Model theModel)
	{
		// get id from pathvariable
		Product theProduct = theProductDao.findById(pId);
		
		// make use  of dao to get the product detail
		// put it in model
		theModel.addAttribute("prod", theProduct);
		
		return "viewProduct";
	}

}
