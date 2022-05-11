package com.etiya.cvWorkNine.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.cvWorkNine.entities.concretes.SocialMediaAccount;

public interface SocialMediaAccDao extends JpaRepository<SocialMediaAccount, Integer> {

}
