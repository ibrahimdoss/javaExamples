package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.User;

public interface UserService {
	
	
	DataResult<List<User>> getAll();
	DataResult<User> getById(int id);
	Result add(User user);
	Result update(User user);
	Result delete(int id);
}
