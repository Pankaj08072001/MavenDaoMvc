package com.training.model;

import java.time.LocalDate;

public class Staff {
	int id;
	String 	name;
	long mobileNo;
	String email;
	String password;
	String 	role;
	String speciality;
	int	managerId ; 
	float salary;
	char gender ;
	LocalDate dob;
	LocalDate joinedAt;
	String description;
	public Staff() {
		super();
	}
	public Staff(int id, String name, long mobileNo, String email, String password, String role, String speciality,
			int managerId, float salary, char gender, LocalDate dob, LocalDate joinedAt, String description) {
		super();
		this.id = id;
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
		this.role = role;
		this.speciality = speciality;
		this.managerId = managerId;
		this.salary = salary;
		this.gender = gender;
		this.dob = dob;
		this.joinedAt = joinedAt;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public LocalDate getJoinedAt() {
		return joinedAt;
	}
	public void setJoinedAt(LocalDate joinedAt) {
		this.joinedAt = joinedAt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", mobileNo=" + mobileNo + ", email=" + email + ", password="
				+ password + ", role=" + role + ", speciality=" + speciality + ", managerId=" + managerId + ", salary="
				+ salary + ", gender=" + gender + ", dob=" + dob + ", joinedAt=" + joinedAt + ", description="
				+ description + "]";
	}

	

}
