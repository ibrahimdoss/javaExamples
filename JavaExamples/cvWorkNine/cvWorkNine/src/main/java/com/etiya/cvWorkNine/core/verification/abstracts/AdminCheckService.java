package com.etiya.cvWorkNine.core.verification.abstracts;

import com.etiya.cvWorkNine.entities.concretes.Employer;

public interface AdminCheckService {
	
	boolean isValid(Employer employer);
}
