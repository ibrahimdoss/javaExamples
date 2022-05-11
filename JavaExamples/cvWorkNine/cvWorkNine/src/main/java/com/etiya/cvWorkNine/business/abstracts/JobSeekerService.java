package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.JobSeeker;

public interface JobSeekerService {
	
	
	DataResult<List<JobSeeker>> getAll();
	Result add(JobSeeker jobSeeker);
}
