package com.csye6225.fall2018.project.jw.cloudProject.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Course;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Program;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Student;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.TempDatebase;

 
public class StudentService {
//     - Every Student has information in the system 
//    - Name        
//    - StudentId
//    - image     
//    - courses enrolled
//    - student name
	public Student getStudent(int studentID) {
		
		return null;
	}
	
static HashMap<Integer, Student> stud_Map = TempDatebase.getStudentDB();
	
	public List<Student> getAllStudents() {	
		//Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for (Student stud : stud_Map.values()) {
			list.add(stud);
		}
		return list ;
	}

	// Adding a student
	public void addStudent(String name,  Date joiningDate) {
		// Next Id 
		int nextAvailableId = stud_Map.size() + 1;
		
		//Create a student Object
		Student stud = new Student(nextAvailableId, name , joiningDate);
			
		stud_Map.put(nextAvailableId, stud);
	}
	
	public List<Course> getStudentCourses(int studentID){
		return null;
		
	}
	public Student addStudent(Student stud) {	
		int nextAvailableId = stud_Map.size() + 1;
		stud.setStudentId(nextAvailableId);
		stud_Map.put(nextAvailableId, stud);
		return stud_Map.get(nextAvailableId);
	}
	
 
	
	// Deleting a Student
	public Student deleteStudent(int studId) {
		Student deletedstudDetails = stud_Map.get(studId);
		stud_Map.remove(studId);
		return deletedstudDetails;
	}
	
	// Updating Student Info
	public Student updateStudentInformation(int studId, Student stud) {	
		Student oldstudObject = stud_Map.get(studId);
		studId = oldstudObject.getStudentId();
		stud.setStudentId(studId);
		stud_Map.put(studId, stud) ;
		return stud;
	}
	
	public void setStudentPorgram(int studId, Program program) {
		Student stud = stud_Map.get(studId);
		stud.setProgram(program);
		 
	}
	
	// Get Students in a department 
	public List<Student> getStudentsByDepartment(String department) {	
		//Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for (Student stud : stud_Map.values()) {
			if (stud.getDepartment().equals(department)) {
				list.add(stud);
			}
		}
		return list ;
	}
}
