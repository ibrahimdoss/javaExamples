package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.Language;

public interface LanguageService {
	
	
	DataResult<List<Language>> getAll();
	DataResult<Language> getById(int id);
	Result add(Language language);
	Result update(Language language);
	Result delete(int id);
}
