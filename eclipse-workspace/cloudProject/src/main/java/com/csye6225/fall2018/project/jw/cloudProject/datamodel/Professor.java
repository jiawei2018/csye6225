package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.List;

import com.csye6225.fall2018.project.jw.cloudProject.resources.Course;

public class Professor {
	private String name;
	private int professorID;
	private List<Course> teachingCourses;

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
	
}
