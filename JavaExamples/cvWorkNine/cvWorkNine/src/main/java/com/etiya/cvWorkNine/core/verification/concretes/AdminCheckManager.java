package com.etiya.cvWorkNine.core.verification.concretes;

import org.springframework.stereotype.Service;

import com.etiya.cvWorkNine.core.verification.abstracts.AdminCheckService;
import com.etiya.cvWorkNine.entities.concretes.Employer;

@Service
public class AdminCheckManager implements AdminCheckService {

	@Override
	public boolean isValid(Employer employer) {
		return true;
	}

}
