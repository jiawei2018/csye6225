package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.List;

import com.csye6225.fall2018.project.jw.cloudProject.resources.Course;
import com.csye6225.fall2018.project.jw.cloudProject.resources.Roster;

public class Program {
	private String name;
	private List<Course> requiredCourses;
	private Roster roster;

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

}
