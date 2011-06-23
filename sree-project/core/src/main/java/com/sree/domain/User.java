/**
 * 
 */
package com.sree.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author srinivasr
 * 
 */

@NamedQueries( { @NamedQuery(name = "findUserByUserName", query = "select user from User user where user.username = ? and user.enabled = 1"), 
	@NamedQuery(name = "findAllUsers", query = "from User user") })
@Entity
@Table(name = "USER")
@SuppressWarnings("serial")
public class User extends BaseDomain implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "USER_NAME", nullable = false, unique = true)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "FIRSTNAME", nullable = false)
	private String firstname;

	@Column(name = "LASTNAME", nullable = false)
	private String lastname;

	@Column(name = "GENDER", nullable = false)
	private Long gender;

	@Column(name = "DOB", nullable = false)
	private Date dob;

	@Column(name = "ACCOUNT_NON_LOCKED")
	private Boolean accountNonLocked = true;

	@Column(name = "ACCOUNT_NON_EXPIRED")
	private Boolean accountNonExpired = true;

	@Column(name = "CREDENTIALS_NON_EXPIRED")
	private Boolean credentialsNonExpired = true;

	@Column(name = "ENABLED")
	private Boolean enabled = true;

	/*
	 * @JoinColumn(name = "USER_ID") @OneToMany(cascade = CascadeType.ALL, fetch =
	 * FetchType.EAGER) private List<UserAuthority> userAuthorities = new
	 * ArrayList<UserAuthority>();
	 */

	@JoinColumn(name = "USER_ID")
	@OneToMany(cascade = CascadeType.ALL)
	List<Address> address = new ArrayList<Address>();

	@Transient
	private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/*
	 * public List<UserAuthority> getUserAuthorities() { return
	 * userAuthorities; }
	 * 
	 * public void setUserAuthorities(List<UserAuthority> userAuthorities) {
	 * this.userAuthorities = userAuthorities; }
	 */

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
