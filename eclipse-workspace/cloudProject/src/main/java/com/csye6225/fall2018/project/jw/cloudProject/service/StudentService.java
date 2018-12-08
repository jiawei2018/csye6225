package com.csye6225.fall2018.project.jw.cloudProject.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Course;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Student;

import aws.lambdas;

 
public class StudentService {
//     - Every Student has information in the system 
//    - Name        
//    - StudentId
//    - image     
//    - courses enrolled
//    - student name
//static HashMap<String, Student> stud_Map = TempDatebase.getStudentDB();

static DynamoDbConnector dynamoDb;
DynamoDBMapper mapper; 

CourseService courseServ = new CourseService();

 

 


 public StudentService() {
	// TODO Auto-generated constructor stub
	dynamoDb = new DynamoDbConnector();
	DynamoDbConnector.init();
	mapper = new DynamoDBMapper(dynamoDb.getClient());
	
}
 
	
 	public Student getStudent(String studentID) {
    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(studentID));

		DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
		    .withIndexName("studentId-index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("studentId = :v1")
		    .withExpressionAttributeValues(eav);

		List<Student> list =  mapper.query(Student.class, queryExpression);
		if(list == null || list.size() < 1) {
			return null;
		}
		return  list.get(0);
	}
	
	public List<Student> getAllStudents() {	
		List<Student> list = new ArrayList<>();
		list.addAll(mapper.scan(Student.class, new DynamoDBScanExpression()));
        return list;
	}

	
	public Student addStudent(Student stud) {	
    	Student stud2 = new Student();
    	//System.out.println(prof);
    	//System.out.println(stud2.getId() + "   <<<<<<<<");
		stud2.setFirstName(stud.getFirstName());
		stud2.setLastName(stud.getLastName());
		stud2.setDepartment(stud.getDepartment());
		stud2.setJoiningDate(stud.getJoiningDate());
		stud2.setRegisteredCourses(stud.getRegisteredCourses());
		stud2.setImage(stud.getImage());
		stud2.setEmail(stud.getEmail());//new field
		stud2.setStudentId(idGenerater.generateId("stud",3));
		System.out.println(stud2);
		mapper.save(stud2);//already have the id  in it
		
		System.out.println("Item added:");
	    //System.out.println(stud2.toString());
	    return stud2;
	}
	
	public Set<String> getStudentCourses(String studentID){
		return getStudent(studentID).getRegisteredCourses();
	}
	
 
 
	
	// Deleting a Student
	public Student deleteStudent(String studId) {
	       Student deletedStudDetails = getStudent(studId);
	       mapper.delete(deletedStudDetails);
	       System.out.println("delete sucess");
	        return deletedStudDetails;
	}
	
	// Updating Student Info
	public Student updateStudentInformation(String studId, Student stud) {
		Student stud2 = getStudent(studId);
        //stud.setId(id);
		System.out.println(" here  is called");
        mapper.save(stud);
        return stud;
	}
	
 
	
	public List<Student> getStudentsByCourse(String courseId) {	
		//Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for (Student stud : getAllStudents()) {
			for(String course : stud.getRegisteredCourses()) {
				if(course.equals(courseId)) {
					list.add(stud);
				}
			}
		}
		return list ;
	}
	
	public List<Course> getStudentAllCourse(String studId ){
		List<Course> res = new ArrayList<>();
		List<Course> allCourse = courseServ.getAllCourses();
		Student stud = getStudent(studId);
		for(String scid : stud.getRegisteredCourses()) {
			for(Course c : allCourse ) {
				if(c.getCourseId().equals(scid)) {
					res.add(c);
				}
			}
		}
		return res;
	}

	private CourseService cService = new CourseService();
	public Student addStudentCourse(String studId, String courseId) {
		Student stud = getStudent(studId);
		 
		if(stud.getRegisteredCourses().size() >= 3) {//no more than 3 courses
			return stud;
		}else {
			Course course = cService.getCourse(courseId);
			
			stud.getRegisteredCourses().add(courseId);
			new lambdas().subscribe(course.getSnsTopic(), stud.getEmail());
			System.out.println("i am here 001");
			updateStudentInformation(studId, stud);
			
			//update rooster
			cService.addStudentToRooster(studId, courseId);//need test
		}
		return stud;
	}
	
	public Student removeStudentCourse(String studId, String courseId) {
		Student stud = getStudent(studId);
		
		if(stud.getRegisteredCourses().size() > 0) {//no more than 3 courses
			stud.getRegisteredCourses().remove(courseId);
			updateStudentInformation(studId, stud);
			String Arn = courseServ.getCourse(courseId).getSnsTopic();
			new lambdas().UnSubscribe(Arn); 
			//update rooster
			cService.removeStudentFromRooster(studId, courseId);//need test
		}else {
			return stud;
		}
		return stud;
	}
	
}
