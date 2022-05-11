package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.Skill;

public interface SkillService {
	
	DataResult<List<Skill>> getAll();
	DataResult<Skill> getById(int id);
	Result add(Skill skill);
	Result update(Skill skill);
	Result delete(int id);
}
