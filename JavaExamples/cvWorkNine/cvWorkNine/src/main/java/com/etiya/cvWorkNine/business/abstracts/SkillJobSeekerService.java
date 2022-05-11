package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.SkillJobSeeker;

public interface SkillJobSeekerService {
	
	DataResult<List<SkillJobSeeker>> getAll();
	DataResult<SkillJobSeeker> getById(int id);
	Result add(int id, SkillJobSeeker  skillJobSeeker);
	Result update(SkillJobSeeker skillJobSeeker);
	Result delete(int id);
}
