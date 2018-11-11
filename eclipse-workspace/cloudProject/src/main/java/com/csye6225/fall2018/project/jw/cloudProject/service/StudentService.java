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
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Professor;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Program;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Student;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.TempDatebase;

 
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

		return list.isEmpty() ? null : list.get(0);
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
	
//	public List<Course> listCourseList(String id) {
//        List<Course> studentCourseList = new ArrayList<>();
//        List<Course> courseList = courseService.listAllCourses();
//        for (String enrolledCourseId : studentService.getStudentById(id).getCourseSet()) {
//            for (Course course : courseList) {
//                if (enrolledCourseId.equals(course.getCourseId())) {
//                    studentCourseList.add(course);
//                    break;
//                }
//            }
//        }
//        return studentCourseList;
//    }
 
	
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
		mapper.delete(stud2);
        stud.setStudentId(studId);
        mapper.save(stud);
        return stud;
	}
	
//	public void setStudentPorgram(String studId, Program program) {
//		Student stud = stud_Map.get(studId);
//		stud.setProgram(program);
//	}
	
	// Get Students in a Course 
	//CourseService cService = new CourseService();
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
}
