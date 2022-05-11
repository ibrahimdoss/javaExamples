package com.etiya.cvWorkNine.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.cvWorkNine.entities.concretes.Resume;

public interface ResumeDao extends JpaRepository<Resume, Integer>{
	
	Resume getByJobSeeker_Id(int id);
}
