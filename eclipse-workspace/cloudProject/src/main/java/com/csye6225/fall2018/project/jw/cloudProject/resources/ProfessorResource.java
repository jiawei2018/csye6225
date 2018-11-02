package com.csye6225.fall2018.project.jw.cloudProject.resources;

//import java.util.Date;
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

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Professor;
import com.csye6225.fall2018.project.jw.cloudProject.service.ProfessorService;

//.. /webapi/myresource
@Path("professors")
public class ProfessorResource {
	ProfessorService profService = new ProfessorService();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessorsByDeparment(@QueryParam("department") String department) {
		if (department == null) {
			return profService.getAllProfessors();
		}
		return profService.getProfessorsByDepartment(department);
	}
	
 
	@GET
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("professorId") int profId) {
		System.out.println(profService.getProfessor(profId));
		return profService.getProfessor(profId);
		 
	}
	
	@DELETE
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor deleteProfessor(@PathParam("professorId") int profId) {
		return profService.deleteProfessor(profId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor addProfessor(Professor prof) {
		System.out.println("   why  here");
		System.out.println(prof);//why para not send in?
			return profService.addProfessor(prof);
	}
	
	@PUT
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(@PathParam("professorId") int profId, 
			Professor prof) {
		return profService.updateProfessorInformation(profId, prof);
	}
	
	
	public void addProfessor(String name, String department, String joiningDate) {
		
		System.out.println("i am not here");
		profService.addProfessor(name, department, joiningDate);
	}
}
