package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.CoverLetter;

public interface CoverLetterService {
	
	DataResult<List<CoverLetter>> getAll();
	DataResult<CoverLetter> getById(int id);
	
	Result add(int id, CoverLetter coverLetter);
	Result update(CoverLetter coverLetter);
	Result delete(int id);
}
