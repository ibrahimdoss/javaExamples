package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.JobAdvertisement;
import com.etiya.cvWorkNine.entities.dtos.JobAdvDto;

public interface JobAdvertisementService {
	
	
	DataResult<List<JobAdvertisement>> getAll();
	DataResult<JobAdvertisement> getById(int id);
	Result add(JobAdvertisement jobAdvertisement);
	Result update(JobAdvertisement jobAdvertisement);
	Result delete(int id);
	Result makePassive(int id);
	DataResult<List<JobAdvertisement>> getByActiveStatus();
	DataResult<List<JobAdvertisement>> getAllSortedByDate();
	DataResult<List<JobAdvertisement>> getByEmployerId(int id);
	DataResult<List<JobAdvertisement>> getActiveAdvByEmployer(int employerId);
	
	DataResult<List<JobAdvDto>> getDtoByActiveStatus();
}
