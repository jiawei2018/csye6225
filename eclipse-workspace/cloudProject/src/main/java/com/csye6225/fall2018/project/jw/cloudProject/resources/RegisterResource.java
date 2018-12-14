package com.csye6225.fall2018.project.jw.cloudProject.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Register;
import com.csye6225.fall2018.project.jw.cloudProject.service.RegisterService;

@Path("registers")
public class RegisterResource {
 

RegisterService RegisterService = new RegisterService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Register> showAll(){
	    return RegisterService.getAllRegisters();
	}
	
	@GET
	@Path("/allRegisters")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Register> getAllRegisters(){
	    return RegisterService.getAllRegisters();
	}
 
	@GET
	@Path("/{RegisterId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Register getRegister(@PathParam("RegisterId") String RegisterId) {
		return RegisterService.getRegister(RegisterId);
	}
	
	@DELETE
	@Path("/{RegisterId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Register deleteRegister(@PathParam("RegisterId") String RegisterId) {
		return RegisterService.deleteRegister(RegisterId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Register addRegister(Register Register) {
			return RegisterService.addRegister(Register);
	}
	
	@PUT
	@Path("/{RegisterId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Register modifyRegister(@PathParam("RegisterId") String RegisterId, 
			Register register) {
		return RegisterService.updateRegisterInformation(RegisterId, register);
	}
}
