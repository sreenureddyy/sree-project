/**
 * 
 */
package com.sree.webservice;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author srinivasr
 * 
 */
@XmlRootElement(name = "employee")
public class Employee {
	private Integer id;

	private String empname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

}
