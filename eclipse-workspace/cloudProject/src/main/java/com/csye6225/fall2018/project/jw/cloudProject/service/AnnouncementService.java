package com.csye6225.fall2018.project.jw.cloudProject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Announcement;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Announcement;

public class AnnouncementService {
	//static HashMap<String, Announcement> prof_Map = TempDatebase.getAnnouncementDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 

	public AnnouncementService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	    public List<Announcement> getAllAnnouncements() {    
	    	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<");
			List<Announcement> list = new ArrayList<>();
			list.addAll(mapper.scan(Announcement.class, new DynamoDBScanExpression()));
	        return list;
	    }
	    
	    public Announcement addAnnouncement(Announcement announcement) {  
	    	Announcement announcement2 = new Announcement();
	    	announcement2.setAnnouncementId(idGenerater.generateId("anno", 3));
	    	if(announcement.getAnnouncementText().length() > 160) {
	    		String text = announcement.getAnnouncementText();
	    		announcement2.setAnnouncementText(text.substring(0, 160) + "  ...can't store more than 160charaters.");
	    	}else {
	    		announcement2.setAnnouncementText(announcement.getAnnouncementText());
	    	}
	    	announcement2.setAnnouncementText(announcement.getAnnouncementText());
	    	announcement2.setBoardId(announcement.getBoardId());
			System.out.println(announcement2);
			mapper.save(announcement2);//already have the id  in it
			System.out.println("Item added:");
		    return announcement2;
	    }
	    
	    public Announcement getAnnouncement(String announcementId) {
	    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":v1",  new AttributeValue().withS(announcementId));

			DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
			    .withIndexName("announcementId-index")
			    .withConsistentRead(false)
			    .withKeyConditionExpression("announcementId = :v1")
			    .withExpressionAttributeValues(eav);

			List<Announcement> list =  mapper.query(Announcement.class, queryExpression);
			return  list.isEmpty() ? null : list.get(0);
	    }
	    
	    public Announcement deleteAnnouncement(String announcementId) {
	       Announcement deletedProfDetails = getAnnouncement(announcementId);
	       mapper.delete(deletedProfDetails);
	       System.out.println("delete sucess");
	       return deletedProfDetails;
	    }
	    
	    public Announcement updateAnnouncementInformation(String announcementId, Announcement anno) {    
	        Announcement oldAnnoObject = getAnnouncement(announcementId);
	        mapper.delete(oldAnnoObject);
	        anno.setAnnouncementId(announcementId);
	        mapper.save(anno);
	        return anno;
	    }
	    
//	    public List<Announcement> getAnnouncementsByDepartment(String boardId) {    
//	        //Getting the list
//	    	HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//			eav.put(":v1",  new AttributeValue().withS(boardId));
//
//			DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
//			    .withIndexName("boardId-index")
//			    .withConsistentRead(false)
//			    .withKeyConditionExpression("boardId = :v1")
//			    .withExpressionAttributeValues(eav);
//
//			List<Announcement> list =  mapper.query(Announcement.class, queryExpression);
//	       return list ;
//	    }

}
