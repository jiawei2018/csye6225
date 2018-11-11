package com.csye6225.fall2018.project.jw.cloudProject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Board;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.DynamoDbConnector;

//Id ? dynamodb
//Board 
//- Id (Dynamo Db generated) and hash key
//- boardId - DynamoDbIndexHashKey, a Global Secondary Index (GSI)
//- courseId
public class BoardService {
	
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 

	public BoardService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	    public List<Board> getAllBoards() {    
			List<Board> list = new ArrayList<>();
			list.addAll(mapper.scan(Board.class, new DynamoDBScanExpression()));
	        return list;
	    }
	    
	    public Board addBoard(Board board) {  
	    	Board Board2 = new Board();
	    	Board2.setBoardId(idGenerater.generateId("bo", 3));
	    	Board2.setCourseId(board.getCourseId());
	    	Board2.setOtherInfo(board.getOtherInfo());
			System.out.println(Board2);
			mapper.save(Board2);//already have the id  in it
			System.out.println("Item added:");
		    return Board2;
	    }
	    
	    public Board getBoard(String BoardId) {
	    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":v1",  new AttributeValue().withS(BoardId));

			DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
			    .withIndexName("boardId-index")
			    .withConsistentRead(false)
			    .withKeyConditionExpression("boardId = :v1")
			    .withExpressionAttributeValues(eav);

			List<Board> list =  mapper.query(Board.class, queryExpression);
			return  list.isEmpty() ? null : list.get(0);
	    }
	    
	    public Board deleteBoard(String BoardId) {
	       Board deletedProfDetails = getBoard(BoardId);
	       mapper.delete(deletedProfDetails);
	       System.out.println("delete sucess");
	       return deletedProfDetails;
	    }
	    
	    public Board updateBoardInformation(String BoardId, Board anno) {    
	        Board oldProfObject = getBoard(BoardId);
	        mapper.delete(oldProfObject);
	        BoardId = oldProfObject.getBoardId();
	        anno.setBoardId(BoardId);
	        mapper.save(anno);
	        return anno;
	    }
	    
//	    public List<Board> getBoardsByDepartment(String boardId) {    
//	        //Getting the list
//	    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//			eav.put(":v1",  new AttributeValue().withS(boardId));
//
//			DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
//			    .withIndexName("boardId-index")
//			    .withConsistentRead(false)
//			    .withKeyConditionExpression("boardId = :v1")
//			    .withExpressionAttributeValues(eav);
//
//			List<Board> list =  mapper.query(Board.class, queryExpression);
//	       return list ;
//	    }

}
