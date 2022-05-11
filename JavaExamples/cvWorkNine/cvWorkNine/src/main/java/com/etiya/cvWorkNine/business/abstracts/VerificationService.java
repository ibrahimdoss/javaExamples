package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.Verification;

public interface VerificationService {
	
	
	DataResult<List<Verification>> getAll();
	DataResult<Verification> getById(int id);
	Result add(Verification verification);
	Result update(Verification verification);
	Result delete(int id);
}
