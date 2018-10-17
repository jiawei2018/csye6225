package com.csye6225.fall2018.project.jw.cloudProject.resources;

import java.util.List;

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Student;

public class Roster {
	public String course;
	public List<Student> enrolledStudents;
	
	
	public int studentsNumber() {
		return enrolledStudents.size();
	}
	
}
