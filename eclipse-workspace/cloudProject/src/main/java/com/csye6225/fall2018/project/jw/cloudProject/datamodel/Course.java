package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.List;
 


 
public class Course {
//    - Every Course has Lectures, and each lecture will have notes, and associated material
//    - Every Course will have a board
//    - Every Course will have a roster 
//    - Every Course has enrolled Students
//    - Every Course has one associated Professor, and a Student TA
	
	private String name;
	private Board board;
	private Roster roster;
	private List<Lecture> lectures;
	private List<Student> enrolledStudents;
	private Professor professor;
	private Student TA;
	private int courseId;
	private String department;
	
	public Course(int courseId, String name, String department) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.courseId = courseId;
		this.department = department;
	}

	public void addStudentToRoster(Student student) {
		enrolledStudents.add(student);
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Roster getRoster() {
		return roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Student getTA() {
		return TA;
	}

	public void setTA(Student tA) {
		TA = tA;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "  CourseId: " + courseId;
	}

}
