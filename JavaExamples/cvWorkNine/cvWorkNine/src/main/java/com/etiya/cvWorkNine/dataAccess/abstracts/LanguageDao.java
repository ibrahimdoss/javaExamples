package com.etiya.cvWorkNine.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.cvWorkNine.entities.concretes.Language;

public interface LanguageDao extends JpaRepository<Language, Integer> {

}
