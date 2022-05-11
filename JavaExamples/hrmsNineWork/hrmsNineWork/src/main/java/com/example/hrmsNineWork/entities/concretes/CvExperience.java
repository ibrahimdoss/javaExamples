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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = " cv_experiences")
public class CvExperience extends Cv {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name=" working_place")
	private String workingPlace;
	
	@Column(name="position")
	private String position;
	
	@Column(name=" start_at")
	private LocalDate startAt;
	
	@Column(name="end_at", nullable=true)
	private LocalDate endAt;
	
	@ManyToOne()
	@JoinColumn(name="jobHunter_id")
	private JobHunter jobHunter;
	
	
	
	
	
	
}
