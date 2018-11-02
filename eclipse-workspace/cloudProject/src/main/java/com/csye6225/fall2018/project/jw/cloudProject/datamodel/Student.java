package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

//import java.util.Date;
import java.util.List;
 

public class Student {
//  - Every Student has information in the system 
// - Name        
// - StudentId
// - image     
// - courses enrolled
// - student name
	private int studentID;
	private String department;
	private String name;
	private List<Image> images;

	private List<Course> enrolledCourse;
	private String joinDate;
	private Program program;
	private boolean isTA = false;
	
	public Student() {
		
	}

	public Student(int studentId, String name, String joiningDate) {
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
		return enrolledCourse.add(c);
	}
	
	public void addEnrolledCourse(List<Course> enrolledCourse) {
		this.enrolledCourse = enrolledCourse;
	}

	public String getDepartment() {
		return department;
	}

	public void setStudentId(int studId) {
		studentID = studId;
	}

	public int getStudentId() {
		return studentID;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "  StudentId: " + studentID;
	}

	public boolean isTA() {
		return isTA;
	}
	
	public void unSetTA() {
		this.isTA = false;
	}
	
	public void setTA() {
		this.isTA = true;
	}

}
