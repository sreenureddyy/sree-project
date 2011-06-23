package com.sree.organization;


import java.util.Date;

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
@Table(name = "COURSE")
@SuppressWarnings("serial")
public class Course extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COURSE_ID")
	private Long id;

	@Column(name = "COURSE")
	private String course;

	@Column(name = "YEAR")
	private Date year = new Date();

	@Column(name = "ACTIVE")
	private Boolean active = true;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
