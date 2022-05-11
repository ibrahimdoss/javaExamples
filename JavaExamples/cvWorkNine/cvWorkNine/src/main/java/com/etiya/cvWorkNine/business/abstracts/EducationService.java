package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.Education;

public interface EducationService {
	
	
	DataResult<List<Education>> getAll();
	DataResult<Education> getById(int id);
	DataResult<List<Education>> getByJobSeekerId(int id);
	Result add(Education education, int jobSeekerId);
	Result update(Education education);
	Result delete(int id);
}
