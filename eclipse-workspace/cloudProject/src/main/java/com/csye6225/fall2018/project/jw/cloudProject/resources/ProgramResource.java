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

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Professor;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Program;
import com.csye6225.fall2018.project.jw.cloudProject.service.ProgramService;

@Path("programs")
public class ProgramResource {
ProgramService progService = new ProgramService();
	



	
	@GET
	@Path("/allprograms")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getAllPrograms(){
	    return progService.getAllPrograms();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getProgramByDeparment(@QueryParam("department") String department	) {
		if (department == null) {
			return progService.getAllPrograms();
		}
		return progService.getProgramsByDepartment(department);
	}
	
 
	@GET
	@Path("/{ProgramId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("ProgramId") String progId) {
		return progService.getProgram(progId);
	}
	
	@DELETE
	@Path("/{ProgramId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("ProgramId") String progId) {
		return progService.deleteProgram(progId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program prog) {
			return progService.addProgram(prog);
	}
	
	@PUT
	@Path("/{ProgramId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("ProgramId") String progId, 
			Program prog) {
		return progService.updateProgramInformation(progId, prog);
	}
	
 
//	public void addProgram(String name, String department, Date joiningDate) {
//		progService.addProgram(name, department);
//	}

}
