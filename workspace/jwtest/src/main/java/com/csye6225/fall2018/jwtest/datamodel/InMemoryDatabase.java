package com.csye6225.fall2018.jwtest.datamodel;

import java.util.HashMap;

public class InMemoryDatabase {
	private static HashMap<Long, Professor> professorDB = new HashMap<> ();

	public static HashMap<Long, Professor> getProfessorDB() {
		return professorDB;
	}

}
