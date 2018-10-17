package com.csye6225.fall2018.project.jw.cloudProject.resources;

import java.util.List;

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Professor;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Student;

public class Course {
	public Board board;
	public Roster roster;
	public List<Lecture> lectures;
	//public List<Student> enrolledStudents;
	public Professor professor;
	public Student TA;
	
	public void addStudentToRoster(Student student) {
		
	}

}
