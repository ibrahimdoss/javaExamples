package com.etiya.cvWorkNine.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.cvWorkNine.entities.concretes.Education;

public interface EducationDao extends JpaRepository<Education, Integer> {

	
	List<Education> getByJobSeeker_Id(int id);
}
