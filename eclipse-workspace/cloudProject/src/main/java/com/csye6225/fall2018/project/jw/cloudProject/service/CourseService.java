package com.csye6225.fall2018.project.jw.cloudProject.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Course;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Professor;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Course;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.TempDatebase;

 

 
public class CourseService {
//static HashMap<String, Course> c_Map = TempDatebase.getCourseDB();

static DynamoDbConnector dynamoDb;
DynamoDBMapper mapper; 

public CourseService() {
	dynamoDb = new DynamoDbConnector();
	//dynamoDb.init();
	DynamoDbConnector.init();
	mapper = new DynamoDBMapper(dynamoDb.getClient());
}
	
	public List<Course> getAllCourses() {	
		List<Course> list = new ArrayList<>();
		list.addAll(mapper.scan(Course.class, new DynamoDBScanExpression()));
        return list;
	}
	
	public Set<Course> getCouseSet(){
		Set<Course> set = new HashSet<>();
		set.addAll(getAllCourses());
		return set;
	}

	public Course addCourse(Course course) {	
    	Course course2 = new Course();
    	//System.out.println(prof);
    	//System.out.println(course2.getId() + "   <<<<<<<<");
		course2.setBoardId(course.getBoardId());
		course2.setCourseId(idGenerater.generateId("co", 3));
		course2.setDepartment(course.getDepartment());
		course2.setLectures(course.getLectures());
		course2.setName(course.getName());
		course2.setProfessorId(course.getProfessorId());
		course2.setTaId(course.getTaId());
		course2.setRoster(course.getRoster());
		System.out.println(course2);
		mapper.save(course2);//already have the id  in it
		
		System.out.println("Item added:");
	    //System.out.println(course2.toString());
	    return course2;
	}
	
	// Getting One Course
	public Course getCourse(String courseId) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(courseId));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
		    .withIndexName("courseId-index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("courseId = :v1")
		    .withExpressionAttributeValues(eav);

		List<Course> list =  mapper.query(Course.class, queryExpression);
		return  list.isEmpty() ? null : list.get(0);
	}
	
	// Deleting a Course
	public Course deleteCourse(String courseId) {
	       Course deletedProfDetails = getCourse(courseId);
	       mapper.delete(deletedProfDetails);
	       System.out.println("delete sucess");
	      return deletedProfDetails;
	}
	
	// Updating Course Info
	public Course updateCourseInformation(String courseId, Course course) {	
        Course oldCourseObject = getCourse(courseId);
        mapper.delete(oldCourseObject);
        course.setCourseId(courseId);
        mapper.save(course);
        return course;
	}
	
	// Get Courses in a department 
	public List<Course> getCoursesByDepartment(String department) {	
    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(department));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
		    .withIndexName("department-index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("department = :v1")
		    .withExpressionAttributeValues(eav);

		List<Course> list =  mapper.query(Course.class, queryExpression);
       return list ;
	}
	
	public List<Course> getCoursesByProfessor(String professorId) {	
    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(professorId));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
		    .withIndexName("professorId-index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("professorId = :v1")
		    .withExpressionAttributeValues(eav);

		List<Course> list =  mapper.query(Course.class, queryExpression);
       return list ;
	}
	
	
	
}
