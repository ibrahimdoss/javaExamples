package com.etiya.cvWorkNine.core.adapters;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.etiya.cvWorkNine.core.utilities.DataResult;

public interface CloudService {
	
	
	DataResult<Map<String, String>> upload(MultipartFile multipartFile);
	@SuppressWarnings("rawtypes")
	DataResult<Map> delete(String id) throws IOException;
}
