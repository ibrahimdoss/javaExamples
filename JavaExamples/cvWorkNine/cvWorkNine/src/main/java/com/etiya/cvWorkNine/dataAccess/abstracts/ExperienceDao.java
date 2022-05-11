package com.etiya.cvWorkNine.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.cvWorkNine.entities.concretes.Experience;

public interface ExperienceDao extends JpaRepository<Experience, Integer> {

}
