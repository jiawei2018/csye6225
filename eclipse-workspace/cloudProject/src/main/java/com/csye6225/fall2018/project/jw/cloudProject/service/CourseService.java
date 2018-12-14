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
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.TempDatebase;

import aws.lambdas;



 

 
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
		
		course2.setCourseId(genCpourseId());
		
		course2.setDepartment(course.getDepartment());
		course2.setLectures(course.getLectures());
		course2.setName(course.getName());
		course2.setProfessorId(course.getProfessorId());
		course2.setTaId(course.getTaId());
		course2.setRoster(course.getRoster());
		
		//get and set arn
		//course2.setSnsTopic(new lambdas().createARN(course.getName()));// assignment 4 -delete
		
		//System.out.println(course2);
		mapper.save(course2);//already have the id  in it
		
		//System.out.println("Item added:");
	    //System.out.println(course2.toString());
	    return course2;
	}
	
	private String genCpourseId() {
		int num = getAllCourses().size();
		String res = String.format("%s%04d", "co" , num);
		System.out.println("<the course id is:> " + res);
		return res;
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
		if(list == null || list.size() < 1) {
			return null;
		}
		return   list.get(0);
	}
	
	public Course deleteCourse(String courseId) {
	       Course deletedProfDetails = getCourse(courseId);
	       mapper.delete(deletedProfDetails);
	       return deletedProfDetails;
	}
	
	public Course updateCourseInformation(String courseId, Course course) {	
        Course oldCourseObject = getCourse(courseId);
        //mapper.delete(oldCourseObject);
        System.out.println(oldCourseObject.getRoster()+ " now the rooster");
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
	
	public void addStudentToRooster(String  studId, String courseId) {
		Course old = getCourse(courseId);
		if(old != null) {
			old.getRoster().add(studId);
			updateCourseInformation(courseId, old);
		}
	}
	
	public void removeStudentFromRooster(String  studId, String courseId) {
		Course old = getCourse(courseId);
		if(old != null) {
			old.getRoster().remove(studId);
			updateCourseInformation(courseId, old);
		}
	}
	
	
	
}
