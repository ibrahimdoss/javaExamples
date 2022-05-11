package com.etiya.cvWorkNine.core.verification.concretes;

import org.springframework.stereotype.Service;

import com.etiya.cvWorkNine.core.verification.abstracts.VerificationCodeService;
import com.etiya.cvWorkNine.entities.concretes.User;

@Service
public class VerificationCodeManager implements VerificationCodeService{

	@Override
	public boolean sendEmail(User user) {
		return true;
	}

}
