package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.Date;
import java.util.List;

 


public class Professor {
	private String name;
	private int professorID;
	private List<Course> teachingCourses;
	private String department;
	private Date joindate;

	public Professor(int nextAvailableId, String name2, String department, Date joiningDate) {
		// TODO Auto-generated constructor stub
		this.department = department;
		this.setJoindate(joiningDate);
		name = name2;
		professorID = nextAvailableId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProfessorID() {
		return professorID;
	}

	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}

	public List<Course> getTeachingCourses() {
		return teachingCourses;
	}

	public void setTeachingCourses(List<Course> teachingCourses) {
		this.teachingCourses = teachingCourses;
	}

	public String getDepartment() {
		// TODO Auto-generated method stub
		return department;
	}

	public int getProfessorId() {
		// TODO Auto-generated method stub
		return professorID;
	}

	public void setProfessorId(int nextAvailableId) {
		// TODO Auto-generated method stub
		
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	
}
