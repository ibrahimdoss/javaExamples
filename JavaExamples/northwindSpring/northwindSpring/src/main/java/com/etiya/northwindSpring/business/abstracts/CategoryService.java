package com.etiya.northwindSpring.business.abstracts;

import java.util.List;

import com.etiya.northwindSpring.core.utilities.results.Result;
import com.etiya.northwindSpring.entities.concretes.Category;

public interface CategoryService {
	
	List<Category> getAll();
	
	Result add(Category category);

}
