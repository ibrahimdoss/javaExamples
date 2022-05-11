package com.etiya.cvWorkNine.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.cvWorkNine.business.abstracts.CityService;
import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.core.utilities.SuccessDataResult;
import com.etiya.cvWorkNine.core.utilities.SuccessResult;
import com.etiya.cvWorkNine.dataAccess.abstracts.CityDao;
import com.etiya.cvWorkNine.entities.concretes.City;

@Service
public class CityManager implements CityService{
	
	private CityDao cityDao;
	
	@Autowired
	public CityManager(CityDao cityDao) {
		super();
		this.cityDao = cityDao;
	}

	@Override
	public DataResult<List<City>> getAll() {
		return new  SuccessDataResult<List<City>>(this.cityDao.findAll(),"city listelendi");
	}

	@Override
	public DataResult<City> getById(int id) {
		return new SuccessDataResult<City>(this.cityDao.findById(id).get());
	}

	@Override
	public Result add(City city) {
		this.cityDao.save(city);
		return new SuccessResult("city eklendi");
	}

	@Override
	public Result update(City city) {
		this.cityDao.save(city);
		return new SuccessResult("city güncellendi");
	}

	@Override
	public Result delete(int id) {
		this.cityDao.deleteById(id);
		return new SuccessResult("city silindi");
	}
}
