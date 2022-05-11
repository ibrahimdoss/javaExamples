package com.etiya.northwindSpring.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.northwindSpring.business.abstracts.ProductService;
import com.etiya.northwindSpring.core.utilities.results.DataResult;
import com.etiya.northwindSpring.core.utilities.results.Result;
import com.etiya.northwindSpring.entities.concretes.Product;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductsController {
	
	private ProductService productService;
	
	@Autowired
	public ProductsController(ProductService productService) {
		super();
		this.productService = productService;
	}

	//@GetMapping("/sayhello")
	//public String sayHello() {
		//return "Hello";		
	//}
	
	@GetMapping("/getall")
	public DataResult<List<Product>> getAll() {
		
		//List<String> products = new ArrayList<String>();
		//products.add("Elma");
		//products.add("Armut");
		//return products;
		
		return this.productService.getAll();
	}
	
	@GetMapping("/getbyproductname")
	public DataResult<List<Product>> getByProductName(String productName) {
		return this.productService.getByProductName(productName);
	}
	
	@GetMapping("/getbycategory")
	public DataResult<List<Product>> getByCategory(int categoryId) {
		return this.productService.getByCategory(categoryId);
	}
	
	@GetMapping("/getbyid")
	public DataResult<Product> getById(int id) {
		return this.productService.getById(id);
	}
	
	
	@GetMapping("/getbyproductnaemandunitprice")
	public DataResult<List<Product>> getByProductNameAndUnitPrice(String productName, double unitPrice) {
		return this.productService.getByProductNameAndUnitPrice(productName, unitPrice);
	}
	
	@GetMapping("/getbyproductnameorcategory")
	public DataResult<List<Product>> getByProductNameOrCategory(String productName, int categoryId) {
		return this.productService.getByProductNameOrCategory(productName, categoryId);
	}
	
	@GetMapping("/getbycategoryin")
	public DataResult<List<Product>> getByCategory_CategoryIdIn(int[] categories) {
		List<Integer> params = new ArrayList<Integer>();
		for (Integer category : categories) {
			params.add(category);
		}
		
		return this.productService.getByCategory_CategoryIdIn(params);
	}
	
	@GetMapping("/getbyproductnamecontains")
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return this.productService.getByProductNameContains(productName);
	}
	
	@GetMapping("/getbyproductnamestartswith")
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		return this.productService.getByProductNameStartsWith(productName);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Product product) {
		return this.productService.add(product);
	}
		
}
