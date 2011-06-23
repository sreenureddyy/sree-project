/**
 * 
 */
package com.sree.drools;

import java.io.Serializable;

/**
 * @author srinivasr
 * 
 */
@SuppressWarnings("serial")
public class EmployeeVo implements Serializable {
	private static String empName;
	private static int empSalary;


	public static String getEmpName() {
		return empName;
	}

	public static void setEmpName(String empName) {
		EmployeeVo.empName = empName;
	}

	public static int getEmpSalary() {
		return empSalary;
	}

	public static void setEmpSalary(int empSalary) {
		EmployeeVo.empSalary = empSalary;
	}

}
