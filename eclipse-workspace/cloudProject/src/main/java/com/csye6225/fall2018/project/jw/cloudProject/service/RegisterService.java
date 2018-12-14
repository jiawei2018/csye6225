package com.csye6225.fall2018.project.jw.cloudProject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Register;

public class RegisterService {
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 

	public   RegisterService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	    public List<Register> getAllRegisters() {    
			List<Register> list = new ArrayList<>();
			list.addAll(mapper.scan(Register.class, new DynamoDBScanExpression()));
	        return list;
	    }
	    
	    private int getNewId() {
	        return getAllRegisters().size() + 1;
	    }
	    
	    public Register addRegister(Register register) {  
	    	Register newRegister = new Register();
	    	newRegister.setId(String.valueOf(getNewId()));
	    	newRegister.setDepartment(register.getDepartment());
	    	newRegister.setOfferingId(register.getOfferingId());//courseId
	    	newRegister.setOfferType(register.getOfferType());
	    	newRegister.setPerUnitPrice(register.getPerUnitPrice());
			System.out.println(newRegister);
			mapper.save(newRegister);//already have the id  in it
		    return newRegister;
	    }
	    
	    
	    
	    public Register getRegister(String registerId) {
	    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":v1",  new AttributeValue().withS(registerId));

			DynamoDBQueryExpression<Register> queryExpression = new DynamoDBQueryExpression<Register>()
			    .withIndexName("id-index")
			    .withConsistentRead(false)
			    .withKeyConditionExpression("id = :v1")
			    .withExpressionAttributeValues(eav);

			List<Register> list =  mapper.query(Register.class, queryExpression);
			if(list == null || list.size() < 1) {
				return null;
			}
			return   list.get(0);
	    }
	    
	    
	    
	    
	    public Register deleteRegister(String registerId) {
	       Register deletedObj = getRegister(registerId);//(BoardId);
	       mapper.delete(deletedObj);
	       System.out.println("delete sucess");
	       return deletedObj;
	    }
	    
	    public Register updateRegisterInformation(String registerId, Register register) {    
	        Register oldProfObject = getRegister(registerId);//(RegisterId);
	   
	        registerId = oldProfObject.getId();
	        register.setId(registerId);//.setBoardId(BoardId);
	        mapper.save(register);
	        return register;
	    }
	    
	     
	    
 

}
