package com.csye6225.fall2018.project.jw.cloudProject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Lecture;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.DynamoDbConnector;

public class LectureService {
	
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 

	public LectureService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	    public List<Lecture> getAllLectures() {    
			List<Lecture> list = new ArrayList<>();
			list.addAll(mapper.scan(Lecture.class, new DynamoDBScanExpression()));
	        return list;
	    }
	    
	    public Lecture addLecture(Lecture lecture) {  
	    	Lecture Lecture2 = new Lecture();
	    	Lecture2.setLectureId(idGenerater.generateId("lec", 3));
	    	Lecture2.setMaterial(lecture.getMaterial());
	    	Lecture2.setNotes(lecture.getNotes());
			System.out.println(Lecture2);
			mapper.save(Lecture2);//already have the id  in it
			System.out.println("Item added:");
		    return Lecture2;
	    }
	    
	    public Lecture getLecture(String LectureId) {
	    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":v1",  new AttributeValue().withS(LectureId));

			DynamoDBQueryExpression<Lecture> queryExpression = new DynamoDBQueryExpression<Lecture>()
			    .withIndexName("lectureId-index")
			    .withConsistentRead(false)
			    .withKeyConditionExpression("lectureId = :v1")//use table attribates
			    .withExpressionAttributeValues(eav);

			List<Lecture> list =  mapper.query(Lecture.class, queryExpression);
			return  list.isEmpty() ? null : list.get(0);
	    }
	    
	    public Lecture deleteLecture(String LectureId) {
	       Lecture deletedProfDetails = getLecture(LectureId);
	       mapper.delete(deletedProfDetails);
	       System.out.println("delete sucess");
	       return deletedProfDetails;
	    }
	    
	    public Lecture updateLectureInformation(String LectureId, Lecture anno) {    
	        Lecture oldProfObject = getLecture(LectureId);
	        mapper.delete(oldProfObject);
	        LectureId = oldProfObject.getLectureId();
	        anno.setLectureId(LectureId);
	        mapper.save(anno);
	        return anno;
	    }
	    
//	    public List<Lecture> getLecturesByDepartment(String boardId) {    
//	        //Getting the list
//	    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//			eav.put(":v1",  new AttributeValue().withS(boardId));
//
//			DynamoDBQueryExpression<Lecture> queryExpression = new DynamoDBQueryExpression<Lecture>()
//			    .withIndexName("boardId-index")
//			    .withConsistentRead(false)
//			    .withKeyConditionExpression("boardId = :v1")
//			    .withExpressionAttributeValues(eav);
//
//			List<Lecture> list =  mapper.query(Lecture.class, queryExpression);
//	       return list ;
//	    }

}
