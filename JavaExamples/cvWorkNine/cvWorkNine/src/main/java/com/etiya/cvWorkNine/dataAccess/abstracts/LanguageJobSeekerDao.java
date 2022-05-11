package com.etiya.cvWorkNine.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.cvWorkNine.entities.concretes.LanguageJobseeker;

public interface LanguageJobSeekerDao extends JpaRepository<LanguageJobseeker, Integer>{

}
