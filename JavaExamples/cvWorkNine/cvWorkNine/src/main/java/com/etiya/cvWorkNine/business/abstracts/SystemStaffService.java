package com.etiya.cvWorkNine.business.abstracts;

import java.util.List;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.SystemStaff;

public interface SystemStaffService {
	
	
	DataResult<List<SystemStaff>> getAll();
	DataResult<SystemStaff> getById(int id);
	Result add(SystemStaff systemStaff);
	Result update(SystemStaff systemStaff);
	Result delete(int id);
}
