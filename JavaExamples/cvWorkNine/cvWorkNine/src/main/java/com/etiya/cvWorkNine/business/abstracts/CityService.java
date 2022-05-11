package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.City;

public interface CityService {
	
	DataResult<List<City>> getAll();
	DataResult<City> getById(int id);
	
	Result add(City city);
	Result update(City city);
	Result delete(int id);
}
