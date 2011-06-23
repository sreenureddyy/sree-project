package com.sree.organization;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sree.domain.BaseDomain;

/**
 * @author srinivasr
 * 
 */
@Entity
@Table(name = "SUBJECT")
@SuppressWarnings("serial")
public class Subject extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUBJECT_ID")
	private Long id;

	@Column(name = "SUBJECT")
	private String subject;


	@Column(name = "ACTIVE")
	private Boolean active = true;
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
