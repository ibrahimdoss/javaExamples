package com.example.hrmsNineWork.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hrmsNineWork.entities.concretes.Staffer;

@Repository
public interface StafferDao  extends JpaRepository<Staffer, Integer>{

}
