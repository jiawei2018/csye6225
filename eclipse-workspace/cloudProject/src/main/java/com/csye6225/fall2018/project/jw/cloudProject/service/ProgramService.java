package com.csye6225.fall2018.project.jw.cloudProject.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.print.DocFlavor.STRING;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Professor;
//import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Professor;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Program;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.TempDatebase;


 
public class ProgramService {
	
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 

	public ProgramService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	public List<Program> getAllPrograms() {	
		//Getting the list
		List<Program> list = new ArrayList<>();
		list.addAll(mapper.scan(Program.class, new DynamoDBScanExpression()));
        return list;
	}


	public Program addProgram(Program prog) {	
    	Program prog2 = new Program();
    	//System.out.println(prog2.getId() + "   <<<<<<<<");
		prog2.setDepartment(prog.getDepartment());
		prog2.setName(prog.getName());;
		prog2.setRequiredCourses(prog.getRequiredCourses()); 
		prog2.setProgramId(idGenerater.generateId("prog", 3)); 
		//System.out.println(prog2);
		mapper.save(prog2);//already have the id  in it
		//System.out.println("Item added:");
	    return prog2;
	}
	
	// Getting One Program
	public Program getProgram(String progId) {
    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(progId));

		DynamoDBQueryExpression<Program> queryExpression = new DynamoDBQueryExpression<Program>()
		    .withIndexName("programId-index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("programId = :v1")
		    .withExpressionAttributeValues(eav);

		List<Program> list =  mapper.query(Program.class, queryExpression);
		if(list == null || list.size() < 1) {
			return null;
		}
		return   list.get(0);
	}
	
	// Deleting a Program
	public Program deleteProgram(String progId) {
	       Program deletedProfDetails = getProgram(progId);
	       mapper.delete(deletedProfDetails);
	       //System.out.println("delete sucess");
	       return deletedProfDetails;
	}
	
	// Updating Program Info
	public Program updateProgramInformation(String progId, Program prog) {	
        Program oldProgObject = getProgram(progId);
        mapper.delete(oldProgObject);
        prog.setProgramId(progId);
        mapper.save(prog);
        return prog;
	}
	
	// Get Programs in a department 
	public List<Program> getProgramsByDepartment(String department) {	
    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(department));

		DynamoDBQueryExpression<Program> queryExpression = new DynamoDBQueryExpression<Program>()
		    .withIndexName("department-index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("department = :v1")
		    .withExpressionAttributeValues(eav);

		List<Program> list =  mapper.query(Program.class, queryExpression);
       return list ;
	}

}
