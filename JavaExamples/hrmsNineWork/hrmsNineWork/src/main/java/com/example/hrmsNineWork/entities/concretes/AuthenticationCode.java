package com.example.hrmsNineWork.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "authentication_codes")

public class AuthenticationCode  {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;

	
	@Column(name = " user_id")
	private int userId;
	
	@Column(name=" code")
	private String code;
	
	@Column(name=" is_confirmed")
	private boolean isConfirmed;
}
