package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.Date;
import java.util.List;
 

public class Student {
//  - Every Student has information in the system 
// - Name        
// - StudentId
// - image     
// - courses enrolled
// - student name
	private int studentID;
	private String name;
	private List<Image> images;
	//private List<Program> programs;
	private List<Course> enrolledCourse;
	private Date joinDate;
	private Program program;

	public Student(int studentId, String name, Date joiningDate) {
		// TODO Auto-generated constructor stub
		this.studentID = studentId;
		this.name = name;
		setJoinDate(joiningDate);
		
	}

	public int getStudentID() {
		return studentID;
	}

 

	public String getName() {
		return name;
	}

 

	public List<Image> getImages() {
		return images;
	}

	public boolean addImages(Image image) {
		return images.add(image);
	}
	
	public boolean deleteImage(int imageIdx) {
		if(imageIdx >= 0 && imageIdx < images.size()) {
			images.remove(imageIdx);
		}
		 return false;
	}


	public Program getProgram() {
		return program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
	}
 

	public List<Course> getEnrolledCourse() {
		return enrolledCourse;
	}

	public boolean addEnrolledCourse( Course c) {
		//this.enrolledCourse = enrolledCourse;
		return enrolledCourse.add(c);
	}
	
	public void addEnrolledCourse(List<Course> enrolledCourse) {
		this.enrolledCourse = enrolledCourse;
	}

 

 

	public Object getDepartment() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStudentId(int studId) {
		// TODO Auto-generated method stub
		studentID = studId;
		
	}

	public int getStudentId() {
		// TODO Auto-generated method stub
		return studentID;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "  StudentId: " + studentID;
	}

}
