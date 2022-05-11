package com.etiya.northwindSpring.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.northwindSpring.business.abstracts.ProductService;
import com.etiya.northwindSpring.core.utilities.results.DataResult;
import com.etiya.northwindSpring.core.utilities.results.ErrorResult;
import com.etiya.northwindSpring.core.utilities.results.Result;
import com.etiya.northwindSpring.core.utilities.results.SuccessDataResult;
import com.etiya.northwindSpring.core.utilities.results.SuccessResult;
import com.etiya.northwindSpring.dataAccess.abstracts.ProductDao;
import com.etiya.northwindSpring.entities.concretes.Product;
import com.etiya.northwindSpring.entities.dtos.ProductDetailDto;

@Service
public class ProductManager implements ProductService {
	
	private ProductDao productDao;
	
	@Autowired
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}
	
	@Override
	public DataResult<List<Product>> getAll() {
		
		List<Product> products = this.productDao.findAll();
		
		return new SuccessDataResult<List<Product>>(products, "ürünler başarıyla listelendi");
		
	}

	@Override
	public DataResult<List<Product>> getByProductName(String productName) {
		List<Product> products = this.productDao.getByProductName(productName);
		return new SuccessDataResult<List<Product>>(products, "ürünler başarıyla listelendi");
	}

	@Override
	public DataResult<Product> getById(int id) {
		Product product = this.productDao.findById(id).get();
		return new SuccessDataResult<Product>(product, "ürün başarıyla listelendi");
	}

	@Override
	public DataResult<List<Product>> getByCategory(int categoryId) {
		List<Product> products = this.productDao.getByCategory_CategoryId(categoryId);
		return new SuccessDataResult<List<Product>>(products, "ürünler başarıyla listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameAndUnitPrice(String productName, double unitPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategory(String productName, int categoryId) {
		return null;
	}

	@Override
	public DataResult<List<Product>> getByCategory_CategoryIdIn(List<Integer> categories) {
		List<Product> products = this.productDao.getByCategory_CategoryIdIn(categories);
		return new SuccessDataResult<List<Product>>(products, "ürünler başarıyla listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
		
		List<Product> products = this.productDao.getByNameAndCategory(productName, categoryId);
		
		return new SuccessDataResult<List<Product>>(products, "ürünler başarıyla listelendi");
		
	}

	@Override
	public DataResult<List<ProductDetailDto>> getProductWithCategoryDetails() {

		List<ProductDetailDto> products = this.productDao.getProductWithCategoryDetails();
		
		return new SuccessDataResult<List<ProductDetailDto>>(products, "ürünler başarıyla listelendi");
	}

	@Override
	public Result add(Product product) {
		if(!nameCheck(product)) {
			return new ErrorResult("aynı isimde bir ürün bulunmaktadır");
		} 
		if(!countUntilTen(product)) {
			return new ErrorResult("bir kategoriye 10'dan fazla ürün ekleyemezsiniz");
		}
		productDao.save(product);		
		return new SuccessResult(product.getProductName() + "ürün eklendi");
		
	}
	
	private boolean nameCheck(Product product) {
		if(productDao.existsProductByProductName(product.getProductName())){
			return false;
		}
		return true;
	}
	
	private boolean countUntilTen(Product product) {
		if(this.productDao.getByCategory_CategoryId(product.getCategory().getCategoryId()).size() >= 10) {
			
			return false;
		}
		
		return true;
		
	}
	

	
//	public boolean countUntilTen(Product product) {
//		if(categoryDao.count(product) {
//			return false;
//		}
//		return true;
//	}



	//@Override
	//public List<Product> getAll() {
		// TODO Auto-generated method stub
		//return this.productDao.findAll();
	//}
	
}
