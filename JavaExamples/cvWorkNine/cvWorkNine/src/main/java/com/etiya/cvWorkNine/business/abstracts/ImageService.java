package com.etiya.cvWorkNine.business.abstracts;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.entities.concretes.Image;

public interface ImageService {
	
	DataResult<List<Image>> getAll();
	DataResult<Image> getById(int id);
	DataResult<List<Image>> getByUserId(int id);
	Result add(MultipartFile multipartFile,int id);
	Result delete(int id) throws IOException;
}
