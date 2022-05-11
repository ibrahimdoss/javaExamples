package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.Job;

public interface JobService {
	
	Result add(Job job);
	DataResult<List<Job>> getAll();
}
