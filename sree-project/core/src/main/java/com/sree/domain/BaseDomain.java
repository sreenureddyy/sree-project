package com.sree.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OrderBy;

@MappedSuperclass
@SuppressWarnings("serial")
public class BaseDomain implements Serializable {
	@Column(name = "CREATEDBY", nullable = false)
	private String createdBy;

	@Column(name = "CREATEDDATETIME", nullable = false)
	@OrderBy(value = "desc")
	private Date createdDatetime = new Date();

	@Column(name = "UPDATEDBY")
	private String updatedBy;

	@Column(name = "UPDATEDDATETIME")
	private Date updatedDatetime  = new Date();

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Date getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(Date updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
