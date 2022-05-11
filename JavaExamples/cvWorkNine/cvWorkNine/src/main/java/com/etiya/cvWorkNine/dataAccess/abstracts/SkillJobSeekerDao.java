package com.etiya.cvWorkNine.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.cvWorkNine.entities.concretes.SkillJobSeeker;

public interface SkillJobSeekerDao extends JpaRepository<SkillJobSeeker, Integer>{

}
