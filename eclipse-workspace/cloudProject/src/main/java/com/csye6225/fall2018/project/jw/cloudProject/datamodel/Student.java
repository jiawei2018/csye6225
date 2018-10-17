package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.List;

import com.csye6225.fall2018.project.jw.cloudProject.resources.Course;
import com.csye6225.fall2018.project.jw.cloudProject.resources.Image;
import com.csye6225.fall2018.project.jw.cloudProject.resources.Program;

public class Student {
	private int StudentID;
	private String Name;
	private List<Image> images;
	private List<Program> programs;
	private List<Course> enrolledCourse;

	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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


	public List<Program> getProgram() {
		return programs;
	}

	public void addProgram(Program program) {
		programs.add(program);
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

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

}
