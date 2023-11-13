package com.example.first.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_details")
public class Employee {
	@Id
	@GeneratedValue
	@Column(name="emp_id")
	private int empID;
	@Column(name="emp_name")
	private String empName;
	@Column(name="emp_designation")
	private String empDesignation;
	@Column(name="emp_dept")
	private int empDept;
	@Column(name="emp_salary")
	private long empSalary;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int empID, String empName, String empDesignation, int empDept, long empSalary) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.empDesignation = empDesignation;
		this.empDept = empDept;
		this.empSalary = empSalary;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public int getEmpDept() {
		return empDept;
	}

	public void setEmpDept(int empDept) {
		this.empDept = empDept;
	}

	public long getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(long empSalary) {
		this.empSalary = empSalary;
	}

	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", empName=" + empName + ", empDesignation=" + empDesignation + ", empDept="
				+ empDept + ", empSalary=" + empSalary + "]";
	}
	
	
}
