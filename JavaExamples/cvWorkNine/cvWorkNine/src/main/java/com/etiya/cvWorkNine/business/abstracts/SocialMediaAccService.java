package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.SocialMediaAccount;

public interface SocialMediaAccService {
	
	DataResult<List<SocialMediaAccount>> getAll();
	DataResult<SocialMediaAccount> getById(int id);
	Result add(int id, SocialMediaAccount socialMedia);
	Result update(SocialMediaAccount socialMedia);
	Result delete(int id);
}
