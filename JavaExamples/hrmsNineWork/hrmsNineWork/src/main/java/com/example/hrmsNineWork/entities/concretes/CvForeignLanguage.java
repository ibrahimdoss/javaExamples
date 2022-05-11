package com.example.hrmsNineWork.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cv_foreign_languages")
public class CvForeignLanguage  extends Cv{
	
	@Id
	@Column(name="id")
	private int id;
	
	
	@Column(name="language")
	private String language;
	
	@Min(value=1)
	@Max(value=5)
	@Column(name="level")
	private int level;
	
	@ManyToOne()
	@JoinColumn(name="jobHunter_id")
	private JobHunter jobHunter;
}
