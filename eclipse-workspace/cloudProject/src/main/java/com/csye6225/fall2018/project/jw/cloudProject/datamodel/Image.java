package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="image")
public class Image {
	
	public String name;
	//public int imageNumber;
	private String imageURL;
	private String imageId;
	public Image() {
	}
	public Image(String name) {
		
	}
	
	 @DynamoDBAttribute(attributeName = "url")
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	 @DynamoDBAttribute(attributeName = "imageId")
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

}
