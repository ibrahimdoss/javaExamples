package com.etiya.cvWorkNine.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.cvWorkNine.entities.concretes.Image;

public interface ImageDao extends JpaRepository<Image, Integer> {
	
	List<Image> getByUser_Id(int id);
}
