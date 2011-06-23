/**
 * 
 */
package com.sree.organization;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sree.domain.BaseDomain;

/**
 * @author YSReddi
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ORGANIZATION")
public class Organization extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORGANIZATION_ID")
	private Long id;

	@Column(name = "ORGANIZATION_NAME")
	private String name;

	@JoinColumn(name = "ORGANIZATION_ID")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Branch> branches = new ArrayList<Branch>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

}
