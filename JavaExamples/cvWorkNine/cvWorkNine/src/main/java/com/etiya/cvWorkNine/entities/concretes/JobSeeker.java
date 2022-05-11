package com.etiya.cvWorkNine.entities.concretes;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="job_seekers")
public class JobSeeker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="national_identity")
	private String nationalIdentity;
	
	@Column(name="birth_date")
	private Date birth_date;
	
	@OneToMany(mappedBy = "jobSeeker")
	private List<Education> educations;
	
	@OneToMany(mappedBy = "jobSeeker")
	private List<SocialMediaAccount> socialMediaAccounts;
	
	@OneToMany(mappedBy = "jobSeeker")
	private List<Experience> experiences;
	
	@OneToMany(mappedBy = "jobSeeker")
	private List<CoverLetter> coverLetters;
	
	@OneToOne()
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "jobSeeker")
	private List<LanguageJobseeker> languageJobseekers;
	
	@OneToMany(mappedBy = "jobSeeker")
	private List<SkillJobSeeker> skillJobSeekers;
}
