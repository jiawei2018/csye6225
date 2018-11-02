package com.csye6225.fall2018.project.jw.cloudProject.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Program;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Student;
import com.csye6225.fall2018.project.jw.cloudProject.service.StudentService;

@Path("students")
public class StudentResource {
StudentService studService = new StudentService();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentByDeparment(
			@QueryParam("department") String department	) {
		if (department == null) {
			return studService.getAllStudents();
		}
		return studService.getStudentsByDepartment(department);
	}
	
 
	@GET
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("StudentId") int profId) {
		return studService.getStudent(profId);
	}
	
	@DELETE
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("StudentId") int profId) {
		return studService.deleteStudent(profId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student prof) {
			return studService.addStudent(prof);
	}
	
	@PUT
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("StudentId") int profId, 
			Student prof) {
		return studService.updateStudentInformation(profId, prof);
	}
	
	
 
	public void setStudentProgram(int studentId, Program program) {
		studService.setStudentPorgram(studentId, program);
	}
	
	
 
	public void addStudent(String name, String department, Date joiningDate) {
		studService.addStudent(name, joiningDate);
	}

}
