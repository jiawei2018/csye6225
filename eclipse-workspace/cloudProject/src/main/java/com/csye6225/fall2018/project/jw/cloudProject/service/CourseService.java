package com.csye6225.fall2018.project.jw.cloudProject.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Course;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.TempDatebase;

 

 
public class CourseService {
static HashMap<Integer, Course> c_Map = TempDatebase.getCourseDB();
	
	public List<Course> getAllcourses() {	
		//Getting the list
		ArrayList<Course> list = new ArrayList<>();
		for (Course course : c_Map.values()) {
			list.add(course);
		}
		return list ;
	}

	// Adding a Course
	public void addCourse(String name, String department) {
		// Next Id 
		int nextAvailableId = c_Map.size() + 1;
		
		//Create a Course Object
		Course course = new Course(nextAvailableId, name, department);
		c_Map.put(nextAvailableId, course);
	}
	
	public Course addCourse(Course course) {	
		int nextAvailableId = c_Map.size() + 1;
		course.setCourseId(nextAvailableId);
		c_Map.put(nextAvailableId, course);
		return c_Map.get(nextAvailableId);
	}
	
	// Getting One Course
	public Course getCourse(int courseId) {
		return c_Map.get(courseId);
	}
	
	// Deleting a Course
	public Course deleteCourse(int courseId) {
		Course deletedcourseDetails = c_Map.get(courseId);
		c_Map.remove(courseId);
		return deletedcourseDetails;
	}
	
	// Updating Course Info
	public Course updateCourseInformation(int courseId, Course course) {	
		Course oldcourseObject = c_Map.get(courseId);
		courseId = oldcourseObject.getCourseId();
		course.setCourseId(courseId);
		// Publishing New Values
		c_Map.put(courseId, course) ;
		
		return course;
	}
	
	// Get Courses in a department 
	public List<Course> getCoursesByDepartment(String department) {	
		//Getting the list
		ArrayList<Course> list = new ArrayList<>();
		for (Course course : c_Map.values()) {
			if (course.getDepartment().equals(department)) {
				list.add(course);
			}
		}
		return list ;
	}
}
