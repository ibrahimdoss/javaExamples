package com.etiya.northwindSpring.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.northwindSpring.business.abstracts.CategoryService;
import com.etiya.northwindSpring.core.utilities.results.ErrorResult;
import com.etiya.northwindSpring.core.utilities.results.Result;
import com.etiya.northwindSpring.core.utilities.results.SuccessResult;
import com.etiya.northwindSpring.dataAccess.abstracts.CategoryDao;
import com.etiya.northwindSpring.entities.concretes.Category;

@Service
public class CategoryManager implements CategoryService {
	
	private CategoryDao categoryDao;
	
	@Autowired
	public CategoryManager(CategoryDao categoryDao) {
		super();
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> getAll() {
		return this.categoryDao.findAll();
	}
	
	@Override
	public Result add(Category category) {
		if(!nameCheck(category)) {
			return new ErrorResult("aynı isimde bir kategori bulunmaktadır");
		}
		if(!countUntilFifteen(category)) {
			return new ErrorResult("15'den fazla kategori ekleyemezsiniz");
		} 
		categoryDao.save(category);		
		return new SuccessResult(category.getCategoryName() + "kategori eklendi");
		
	}
	
	public boolean nameCheck(Category category) {
		if(categoryDao.existsCategoryByCategoryName(category.getCategoryName())){
			return false;
		}
		return true;
	}
	
	public boolean countUntilFifteen(Category category) {
		if(categoryDao.count() > 15) {
			return false;
		}
		return true;
	}
	
}
