package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.Resume;

public interface ResumeService {
	
	DataResult<List<Resume>> getAll();
	DataResult<Resume> getById(int id);
	DataResult<Resume> getByJobSeekerId(int id);
	
	Result add(Resume resume);
	Result update(Resume resume);
	Result delete(int id);
}
