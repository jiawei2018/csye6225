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

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Course;
import com.csye6225.fall2018.project.jw.cloudProject.service.CourseService;

@Path("course")
public class CourseResource {
	CourseService courseService = new CourseService();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourseByDeparment(
			@QueryParam("department") String department			) {
		if (department == null) {
			return courseService.getAllcourses();
		}
		return courseService.getCoursesByDepartment(department);
	}
	
 
	@GET
	@Path("/{CourseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("CourseId") int courseId) {
		return courseService.getCourse(courseId);
	}
	
	@DELETE
	@Path("/{CourseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course deleteCourse(@PathParam("CourseId") int courseId) {
		return courseService.deleteCourse(courseId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addCourse(Course course) {
			return courseService.addCourse(course);
	}
	
	@PUT
	@Path("/{CourseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course updateCourse(@PathParam("CourseId") int courseId, 
			Course course) {
		return courseService.updateCourseInformation(courseId, course);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCourse(String name, String department, Date joiningDate) {
		courseService.addCourse(name, department);
	}


}
