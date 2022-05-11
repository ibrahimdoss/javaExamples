package com.etiya.northwindSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.northwindSpring.entities.concretes.Product;
import com.etiya.northwindSpring.entities.dtos.ProductDetailDto;

public interface ProductDao extends JpaRepository<Product, Integer> {
	//getBy || findBy
	List<Product> getByProductName(String productName);
	//select * from products where productName = @productName limit 1
	
	List<Product> getByCategory_CategoryId(int categoryId);	
	
	Product getById(int id);
	
	
	List<Product> getByProductNameAndUnitPrice(String productName, double unitPrice);
	
	List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);
	  
	List<Product> getByCategory_CategoryIdIn(List<Integer> categories);
	  
	List<Product> getByProductNameContains(String productName);
	  
	List<Product> getByProductNameStartsWith(String productName);
	
	//JPQL - kompleks sorgular için
	//JPQL - Select * from products
	@Query("From Product where productName=:productName and category.categoryId=:categoryId")
	List<Product> getByNameAndCategory(String productName, int categoryId);
	  
	@Query("Select new com.etiya.northwindSpring.entities.dtos.ProductDetailDto"
			+ "(p.id, p.productName, c.categoryName, p.unitPrice) "
	  		+ "From Category c Inner Join c.products p")
	List<ProductDetailDto> getProductWithCategoryDetails();
	
	boolean existsProductByProductName(String productName);
	
		  
	//select p.productId,p.productName, c.categoryName  from Category c inner join Product p
	//on c.categoryId = p.categoryId
	  
}

//reflection

//postgre tablosu oluştur -> todo_app_db
//todos tablosu oluştur
//spring boot app oluştur
//swagger desteği getir
//getall
//getByUserId
//getByCompleted
