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

//import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Professor;
//import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Program;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.*;
import com.csye6225.fall2018.project.jw.cloudProject.service.StudentService;

@Path("students")
public class StudentResource {
StudentService studService = new StudentService();
	
	
 	@GET
 	@Path("/allstudents")
 	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudents(){
 			return studService.getAllStudents();//.getAllStudents();
	}
 
 
	@GET
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("StudentId") String studId) {
		return studService.getStudent(studId);//.getStudent(studId);
	}
	
	@GET
	@Path("/{StudentId}/courses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getStudentCouses(@PathParam("StudentId") String studId) {
		return studService.getStudentAllCourse(studId);
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudentByCourse(@QueryParam("courseId") String courseId) {
        if (courseId == null) {
            return studService.getAllStudents();
        }
        return studService.getStudentsByCourse(courseId);
    }
	   
	@DELETE
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("StudentId") String studId) {
		return studService.deleteStudent(studId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student student) {
			return studService.addStudent(student);
	}
	

	
	
	@PUT
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("StudentId") String studId, 
			Student stud) {
		return studService.updateStudentInformation(studId, stud);
	}
	
	@PUT
	@Path("/{StudentId}/{CourseId}/register")  //new field
	//students?StudentId=bo940&CourseId=utf-8
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudentCourse(@PathParam("StudentId") String studId, @PathParam("CourseId") String courseId) {
		return studService.addStudentCourse(studId, courseId);
	}
	
	@DELETE
	@Path("/{StudentId}/{CourseId}/drop")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student delStudentCourse(@PathParam("StudentId") String studId, @PathParam("CourseId") String courseId) {
		return studService.removeStudentCourse(studId, courseId);
	}

}
