package com.example.hrmsNineWork.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name="job_adverts")
public class JobAdvert extends Cv{
	
	@Id
	
	
	@Column(name="description")
	private String description;
	
	@Column(name="salary_min")
	private int salaryMin;
	
	@Column(name="salary_max")
	private int salaryMax;
	
	@Column(name="open_position_count")
	private int openPositionCount;
	
	@Column(name="deadline")
	private LocalDate deadline;
	
	@Column(name="published_at")
	private LocalDate publishedAt;
	
	@Column(name="is_open")
	private boolean isOpen;
	
	
	
	@ManyToOne()
	@JoinColumn(name="employer_id")
	private Employer employer;
	
	@ManyToOne()
	@JoinColumn(name="city_id")
	private City city;
}
