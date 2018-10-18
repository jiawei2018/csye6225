package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

//import java.util.Date;
import java.util.List;
 
public class Program {
	private String name;
	private List<Course> requiredCourses;
	private Roster roster;
	private String department;
	private int programId;
	
	public Program(int porgramId, String name, String department) {
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return programId;
	}

	public String getDepartment() {
		// TODO Auto-generated method stub
		return department;
	}
	@Override
	public String toString() {
		return name;
		
	}

}
