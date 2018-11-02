package com.csye6225.fall2018.project.jw.cloudProject.service;
import java.util.ArrayList;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
 

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Professor;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.TempDatebase;

 
 
public class ProfessorService {

	static HashMap<Integer, Professor> prof_Map = TempDatebase.getProfessorDB();
	
	public List<Professor> getAllProfessors() {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			list.add(prof);
		}
		return list ;
	}

	// Adding a professor
	public void addProfessor(String name, String department, String joiningDate) {
		// Next Id 
		int nextAvailableId = prof_Map.size() + 1;
		
		//Create a Professor Object
		Professor prof = new Professor(nextAvailableId, name, department, joiningDate);
		System.out.println(" here ? 1");
		prof_Map.put(nextAvailableId, prof);
	}
	
	public Professor addProfessor(Professor prof) {	
		System.out.println(" here ? 2");
		int nextAvailableId = prof_Map.size() + 1;
		prof.setProfessorId(nextAvailableId);
		prof_Map.put(nextAvailableId, prof);
		return prof_Map.get(nextAvailableId);
	}
	
	// Getting One Professor
	public Professor getProfessor(int profId) {
		System.out.println(prof_Map.get(profId));
		return prof_Map.get(profId);
	}
	
	// Deleting a professor
	public Professor deleteProfessor(int profId) {
		Professor deletedProfDetails = prof_Map.get(profId);
		prof_Map.remove(profId);
		return deletedProfDetails;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(int profId, Professor prof) {	
		Professor oldProfObject = prof_Map.get(profId);
		profId = oldProfObject.getProfessorId();
		prof.setProfessorId(profId);
		// Publishing New Values
		prof_Map.put(profId, prof) ;
		
		return prof;
	}
	
	// Get professors in a department 
	public List<Professor> getProfessorsByDepartment(String department) {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list ;
	}

}
