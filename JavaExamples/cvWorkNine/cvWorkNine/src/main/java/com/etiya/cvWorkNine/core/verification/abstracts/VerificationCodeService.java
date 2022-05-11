package com.etiya.cvWorkNine.core.verification.abstracts;

import com.etiya.cvWorkNine.entities.concretes.User;

public interface VerificationCodeService {
	
	
	boolean sendEmail(User user);
}
