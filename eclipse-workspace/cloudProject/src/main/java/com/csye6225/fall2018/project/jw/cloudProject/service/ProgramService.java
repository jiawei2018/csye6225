package com.csye6225.fall2018.project.jw.cloudProject.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Program;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.TempDatebase;


 
public class ProgramService {
	static HashMap<Integer, Program> prog_Map = TempDatebase.getProgramDB();
	
	public List<Program> getAllPrograms() {	
		//Getting the list
		ArrayList<Program> list = new ArrayList<>();
		for (Program prog : prog_Map.values()) {
			list.add(prog);
		}
		return list ;
	}

	// Adding a Program
	public void addProgram(String name, String department) {
		// Next Id 
		int nextAvailableId = prog_Map.size() + 1;
		
		//Create a Program Object
		Program prog = new Program(nextAvailableId, name, department);
		prog_Map.put(nextAvailableId, prog);
	}
	
	public Program addProgram(Program prog) {	
		int nextAvailableId = prog_Map.size() + 1;
		prog.setProgramId(nextAvailableId);
		prog_Map.put(nextAvailableId, prog);
		return prog_Map.get(nextAvailableId);
	}
	
	// Getting One Program
	public Program getProgram(int progId) {
		return prog_Map.get(progId);
	}
	
	// Deleting a Program
	public Program deleteProgram(int progId) {
		Program deletedprogDetails = prog_Map.get(progId);
		prog_Map.remove(progId);
		return deletedprogDetails;
	}
	
	// Updating Program Info
	public Program updateProgramInformation(int progId, Program prog) {	
		Program oldprogObject = prog_Map.get(progId);
		progId = oldprogObject.getProgramId();
		prog.setProgramId(progId);
		// Publishing New Values
		prog_Map.put(progId, prog) ;
		
		return prog;
	}
	
	// Get Programs in a department 
	public List<Program> getProgramsByDepartment(String department) {	
		//Getting the list
		ArrayList<Program> list = new ArrayList<>();
		for (Program prog : prog_Map.values()) {
			if (prog.getDepartment().equals(department)) {
				list.add(prog);
			}
		}
		return list ;
	}

}
