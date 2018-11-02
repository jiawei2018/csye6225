package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.List;
 
public class Program {
	private String name;
	private List<Course> requiredCourses;
	private Roster roster;
	private String department;
	private int programId;
	
	public Program() {
		
	}
	
	public Program(int porgramId, String name, String department) {
		this.programId = porgramId;
		this.name = name;
		this.department = department;
	}

 
	public String getName() {
		return name;
	}

 
	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getRequiredCourses() {
		return requiredCourses;
	}

	public void setRequiredCourses(List<Course> requiredCourses) {
		this.requiredCourses = requiredCourses;
	}

	public Roster getRoster() {
		return roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	public void setProgramId(int programId) {
		 this.programId = programId;
	}

	public int getProgramId() {
		return programId;
	}

	public String getDepartment() {
		return department;
	}
	
	@Override
	public String toString() {
		return name;
		
	}

}
