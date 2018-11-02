package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

//import java.util.Date;
import java.util.List;

public class Professor {
	private String name;
	private int professorId;
	private List<Course> teachingCourses;
	private String department;
	private String joindate;
	
	public Professor() {
	}
	
	public Professor(int nextAvailableId, String name2, String department, String joiningDate) {
		System.out.println(" here ? constructor");
		this.department = department;
		setJoindate(joiningDate);
		name = name2;
		this.professorId = nextAvailableId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getprofessorId() {
		return professorId;
	}

	public void setprofessorId(int professorId) {
		this.professorId = professorId;
	}

	public List<Course> getTeachingCourses() {
		return teachingCourses;
	}

	public void setTeachingCourses(List<Course> teachingCourses) {
		this.teachingCourses = teachingCourses;
	}

	public String getDepartment() {
		return department;
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
		
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		System.out.println(joindate + "   <<<");
		
		this.joindate = joindate;
	}
	
	@Override
	public String toString() {
		return "  proID=   " + getprofessorId()+"  ,firstName=" + getName() + "   department:" + getDepartment()
		+ ",joindate   " + getJoindate();
	}
	
}
