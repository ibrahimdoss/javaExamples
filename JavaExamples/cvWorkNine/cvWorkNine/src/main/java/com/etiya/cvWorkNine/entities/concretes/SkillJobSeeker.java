package com.etiya.cvWorkNine.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="skill_jobseeker")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobSeeker"})
public class SkillJobSeeker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne()
	@JoinColumn(name="jobseeker_id")
	JobSeeker jobSeeker;
	
	@ManyToOne()
	@JoinColumn(name="skill_id")
	 Skill skill;
}
