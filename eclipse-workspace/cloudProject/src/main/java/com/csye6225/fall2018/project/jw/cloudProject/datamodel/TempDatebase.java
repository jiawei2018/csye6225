package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.HashMap;

public class TempDatebase {
	private static HashMap<Integer, Professor> professorDB = new HashMap<> ();
	private static HashMap<Integer, Student> studentDB = new HashMap<>();
	private static HashMap<Integer, Program> ProgramDB = new HashMap<>();
	private static HashMap<Integer, Course> courseDB = new HashMap<>();

	public static HashMap<Integer, Professor> getProfessorDB() {
		return professorDB;
	}

	public static HashMap<Integer, Student> getStudentDB() {
		return studentDB;
	}

	public static HashMap<Integer, Program> getProgramDB() {
		return ProgramDB;
	}

	public static HashMap<Integer, Course> getCourseDB() {
		return courseDB;
	}

	public static void setCourseDB(HashMap<Integer, Course> courseDB) {
		TempDatebase.courseDB = courseDB;
	}
}
