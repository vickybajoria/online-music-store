package com.vickyproject.onlinemusicstore.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping("admin")
	public String admin()
	{
		return "admin";
	}
	
	@RequestMapping("admin/productInventory")
	public String productInventory(Model theModel)
	{
		List<Product> prodList = theProductDao.findAll();
		
		theModel.addAttribute("prodList", prodList);
		
		return "productInventory";
	}
	
	@RequestMapping("/admin/showProductAddForm")
	public String showProductAddForm(Model theModel)
	{
		Product theProduct = new Product();
		
		theModel.addAttribute("product", theProduct);
		
		return "addProduct";
	}
	
	@PostMapping("/admin/processAddProductForm")
	public String saveProduct(@ModelAttribute("product") Product theProduct, BindingResult result, 
			                  HttpServletRequest request)
	{
		if(result.hasErrors())
			return "addProduct";
		 
		theProductDao.save(theProduct);
		
		MultipartFile productImage = theProduct.getProductImage();
		
		if (!productImage.isEmpty()) {
		
		 try {

	            // Get the file and save it somewhere
			       Path currentPath = Paths.get(".");
			       Path absolutePath = currentPath.toAbsolutePath();
			       
	            byte[] bytes = productImage.getBytes();
	            Path path = Paths.get(absolutePath + "/src/main/resources/static/uploads/" + theProduct.getProductId() + ".png");
	            Files.write(path, bytes);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		return "redirect:/online-music-store/admin/productInventory";
	}
	
	@RequestMapping("/admin/deleteProduct/{pId}")
	public String deleteProduct(@PathVariable int pId)
	{
		// delete
		Product theProduct = theProductDao.findById(pId);
		if(theProduct == null)
		{
			throw new RuntimeException("Product id not found: " + pId);
		}
		
		// Get the file and save it somewhere
		  Path currentPath = Paths.get(".");
		  Path absolutePath = currentPath.toAbsolutePath();
		  
		  Path path = Paths.get(absolutePath + "/src/main/resources/static/uploads/" + theProduct.getProductId() + ".png");
		  
		  if (Files.exists(path)) {
	            try {
	                Files.delete(path);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		
		theProductDao.deleteById(pId);
		
		return "redirect:/online-music-store/admin/productInventory";
	}
	
	
	
	
	
	

}
