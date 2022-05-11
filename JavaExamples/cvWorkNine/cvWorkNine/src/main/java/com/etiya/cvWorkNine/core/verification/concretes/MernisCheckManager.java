package com.etiya.cvWorkNine.core.verification.concretes;

import org.springframework.stereotype.Service;

import com.etiya.cvWorkNine.core.verification.abstracts.MernisCheckService;
import com.etiya.cvWorkNine.entities.concretes.JobSeeker;

@Service
public class MernisCheckManager implements MernisCheckService{

	@Override
	public boolean checkIfRealPerson(JobSeeker jobSeeker) {
		// TODO Auto-generated method stub
		return true;
	}

}
