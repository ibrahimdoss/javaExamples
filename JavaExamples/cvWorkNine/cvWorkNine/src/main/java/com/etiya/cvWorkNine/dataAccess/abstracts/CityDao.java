package com.etiya.cvWorkNine.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.cvWorkNine.entities.concretes.City;

public interface CityDao extends JpaRepository<City, Integer> {

}
