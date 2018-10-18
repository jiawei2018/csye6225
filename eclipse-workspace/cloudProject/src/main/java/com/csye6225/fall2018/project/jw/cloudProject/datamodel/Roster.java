package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Roster {
	private String rosterCourse;
	private List<Student> enrolledStudents;
	
	
	public Roster(String rosterCourse) {
		this.rosterCourse = rosterCourse;
	}
	public int studentsNumber() {
		return enrolledStudents.size();
	}
	public void setEnrolledList(List<Student> list) {
		enrolledStudents = new ArrayList<>();
		enrolledStudents = list;
	}
	public List<Student> getEnrolledStudents(){
		return enrolledStudents;
	}
	
	public void addStudentTORoster(Student student) {
		enrolledStudents.add(student);
	}
	
	public void deleteStudentFromRoster(Student student) {
		if(enrolledStudents.contains(student)) {
			enrolledStudents.remove(student);
		}
	}
	@Override
	public String toString() {
		return "Roster: " + rosterCourse + "  StudentsNum: " + enrolledStudents.size();
	}
	
	
}
