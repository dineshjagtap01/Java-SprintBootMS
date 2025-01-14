package com.empapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employees {
	
	@Id
	private int empId;
	private String empName;
	private String email;
	private String grade;
	private int joiningYear;
	private String departement;
	public Employees(int empId, String empName, String email, String grade, int joiningYear, String departement) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.email = email;
		this.grade = grade;
		this.joiningYear = joiningYear;
		this.departement = departement;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getJoiningYear() {
		return joiningYear;
	}
	public void setJoiningYear(int joiningYear) {
		this.joiningYear = joiningYear;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}
}
