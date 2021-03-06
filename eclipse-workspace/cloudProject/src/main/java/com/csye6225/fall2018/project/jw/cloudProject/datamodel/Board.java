package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="board")
public class Board {

	private String id;
	private String boardId;
	private String courseId;
	//private String courseId;
	private String otherInfo;
	
	
	public Board() {
	}
	public Board(String boardId, String courseId )	{
		this.boardId = boardId;
		this.courseId = courseId;
		//this.courseName = courseName;
	}
	public Board(String  boardId, String courseId, String otherInfo) {
		this.boardId = boardId;
		this.courseId = courseId;
		this.otherInfo = otherInfo;
	}
	
	
	@DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	@DynamoDBIndexHashKey(attributeName = "boardId", globalSecondaryIndexName = "boardId-index")
    public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	@DynamoDBAttribute(attributeName = "courseId")
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@DynamoDBAttribute(attributeName = "otherInfo")
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	

}
