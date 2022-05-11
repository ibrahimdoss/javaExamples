package com.etiya.cvWorkNine.core.verification.abstracts;

import com.etiya.cvWorkNine.entities.concretes.JobSeeker;

public interface MernisCheckService {
	
	boolean checkIfRealPerson(JobSeeker jobSeeker);
}
