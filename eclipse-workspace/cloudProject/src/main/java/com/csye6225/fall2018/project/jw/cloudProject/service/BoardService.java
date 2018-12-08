package com.csye6225.fall2018.project.jw.cloudProject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
			if(list == null || list.size() < 1) {
				return null;
			}
			return   list.get(0);
	    }
	    
	    public Board deleteBoard(String BoardId) {
	       Board deletedProfDetails = getBoard(BoardId);
	       mapper.delete(deletedProfDetails);
	       System.out.println("delete sucess");
	       return deletedProfDetails;
	    }
	    
	    public Board updateBoardInformation(String BoardId, Board board) {    
	        Board oldProfObject = getBoard(BoardId);
	   
	        BoardId = oldProfObject.getBoardId();
	        board.setBoardId(BoardId);
	        mapper.save(board);
	        return board;
	    }
	    public Board modifyBoardCourseId(String BoardId, String courseId) {
	    	Board board = getBoard(BoardId);
	    	board.setCourseId(courseId);
	    	mapper.save(board);
	    	return board;
	    }
	    
//	    public Board addCourseToBoard(String boardId, String courseId) {
//	    	Board oldBoard = getBoard(boardId);
//	    	oldBoard.getCourseId().add(courseId);
//	    	updateBoardInformation(boardId, oldBoard);
//	    	return oldBoard;
//	    }
//	    
//	    public Board removeCourseFromBoard(String boardId, String courseId) {
//	    	Board oldBoard = getBoard(boardId);
//	    	oldBoard.getCourseId().remove(courseId);
//	    	updateBoardInformation(boardId, oldBoard);
//	    	return oldBoard;
//	    }
	    
 

}
