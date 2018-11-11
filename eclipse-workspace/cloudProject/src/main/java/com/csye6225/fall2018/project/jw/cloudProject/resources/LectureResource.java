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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Lecture;

//import com.csye6225.fall2018.project.jw.cloudProject.service.lectureService;
import com.csye6225.fall2018.project.jw.cloudProject.service.LectureService;

@Path("lectures")
public class LectureResource {
LectureService lectureService = new LectureService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lecture> showAll(){
	    return lectureService.getAllLectures();
	}
	
	@GET
	@Path("/alllectures")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lecture> getAllLecturess(){
	    return lectureService.getAllLectures();
	}
 
	@GET
	@Path("/{lectureId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture getCourse(@PathParam("lectureId") String lectureId) {
		System.out.println(lectureId);
		return lectureService.getLecture(lectureId);
	}
	
	@DELETE
	@Path("/{lectureId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture deleteCourse(@PathParam("lectureId") String lectureId) {
		return lectureService.deleteLecture(lectureId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Lecture addCourse(Lecture lecture) {
			return lectureService.addLecture(lecture);
	}
	
	@PUT
	@Path("/{lectureId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Lecture updateCourse(@PathParam("lectureId") String lectureId, 
			Lecture lecture) {
		return lectureService.updateLectureInformation(lectureId, lecture);
	}


}
