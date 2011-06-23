/**
 * 
 */
package com.sree.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author srinivasr
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CONTACT_DETAILS")
public class ContactDetails extends BaseDomain{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="CONTACT_ID")
	private Long contactId;
	
	@Column(name="CONTACT_TYPE")
	private Long contactType;
	
	@Column(name="CONTACT")
	private String contact;
	
	@Column(name="IS_PRIMARY")
	private Boolean isPrimary = true;
	
	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public Long getContactType() {
		return contactType;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public void setContactType(Long contactType) {
		this.contactType = contactType;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	
	
}
