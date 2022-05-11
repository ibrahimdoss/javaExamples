package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.LanguageJobseeker;

public interface LanguageJobSeekerService {
	
	DataResult<List<LanguageJobseeker>> getAll();
	DataResult<LanguageJobseeker> getById(int id);
	Result add(int id,LanguageJobseeker  languageJobseeker);
	Result update(LanguageJobseeker languageJobseeker);
	Result delete(int id);
}
