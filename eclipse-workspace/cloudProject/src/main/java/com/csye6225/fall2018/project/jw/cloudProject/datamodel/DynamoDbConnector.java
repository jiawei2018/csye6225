package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
//import com.amazonaws.auth.SdkClock.Instance;

//import org.eclipse.persistence.internal.libraries.asm.commons.StaticInitMerger;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import com.amazonaws.ser
 

public class DynamoDbConnector {
	static AmazonDynamoDB dynamoDB; 
	public static  void init() {
		if(dynamoDB == null) {
		// cloud use
		//InstanceProfileCredentialsProvider 	credentialsProvider = new InstanceProfileCredentialsProvider(false);
		
			
		//local use
		//ProfileCredentialsProvider	credentialsProvider = new ProfileCredentialsProvider();
		DefaultAWSCredentialsProviderChain credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
		credentialsProvider.getCredentials();
		
		dynamoDB =  AmazonDynamoDBClientBuilder
				.standard()
				.withCredentials(credentialsProvider)
				.withRegion("us-east-2")
				.build();
		System.out.println("I created the client -- dynamodb connector");
		} 
	}
	
	public AmazonDynamoDB getClient() {
		return dynamoDB;
	}

}
