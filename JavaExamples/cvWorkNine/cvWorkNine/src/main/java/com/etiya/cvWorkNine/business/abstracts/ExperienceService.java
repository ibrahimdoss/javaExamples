package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.Experience;

public interface ExperienceService {
	
	DataResult<List<Experience>> getAll();
	DataResult<Experience> getById(int id);
	Result add(Experience experience , int id);
	Result update(Exception exception);
	Result delete(int id);
}
