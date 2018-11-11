package com.csye6225.fall2018.project.jw.cloudProject.service;
import java.util.ArrayList;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Professor;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.TempDatebase;

 
 
public class ProfessorService {

//static HashMap<String, Professor> prof_Map = TempDatebase.getProfessorDB();
static DynamoDbConnector dynamoDb;
DynamoDBMapper mapper; 

public ProfessorService() {
	dynamoDb = new DynamoDbConnector();
	DynamoDbConnector.init();
	mapper = new DynamoDBMapper(dynamoDb.getClient());
}
    // Getting a list of all professor
    // GET "..webapi/professors"
    public List<Professor> getAllProfessors() {    
        //Getting the list
		List<Professor> list = new ArrayList<>();
		list.addAll(mapper.scan(Professor.class, new DynamoDBScanExpression()));
        return list;
    }
    
    public Professor addProfessor(Professor prof) {  
    	Professor prof2 = new Professor();
    	//System.out.println(prof);
    	//System.out.println(prof2.getId() + "   <<<<<<<<");
		prof2.setFirstName(prof.getFirstName());
		prof2.setLastName(prof.getLastName());
		prof2.setDepartment(prof.getDepartment());
		prof2.setJoiningDate(prof.getJoiningDate());
		prof2.setProfessorId(idGenerater.generateId("prof",3));
		System.out.println(prof2);
		mapper.save(prof2);//already have the id  in it
		
		System.out.println("Item added:");
	    //System.out.println(prof2.toString());
	    return prof2;
    }
    
    // Getting One Professor
    public Professor getProfessor(String profId) {
    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(profId));

		DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor>()
		    .withIndexName("professorId-index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("professorId = :v1")
		    .withExpressionAttributeValues(eav);

		List<Professor> list =  mapper.query(Professor.class, queryExpression);
		if(list.size() > 1) {
			//wrong
			throw new IllegalArgumentException();
		}
		return  list.isEmpty() ? null : list.get(0);
    }
    
    // Deleting a professor
    public Professor deleteProfessor(String profId) {
       Professor deletedProfDetails = getProfessor(profId);
//        prof_Map.remove(profId);
       mapper.delete(deletedProfDetails);
       System.out.println("delete sucess");
        return deletedProfDetails;
    }
    
    // Updating Professor Info
    public Professor updateProfessorInformation(String profId, Professor prof) {    
        Professor oldProfObject = getProfessor(profId);
        mapper.delete(oldProfObject);
        prof.setProfessorId(profId);
        mapper.save(prof);
        return prof;
    }
    
    // Get professors in a department
    public List<Professor> getProfessorsByDepartment(String department) {    
        //Getting the list
    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(department));

		DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor>()
		    .withIndexName("department-index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("department = :v1")
		    .withExpressionAttributeValues(eav);

		List<Professor> list =  mapper.query(Professor.class, queryExpression);
       return list ;
    }
    

}
