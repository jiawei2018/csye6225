package aws;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import com.amazonaws.services.dynamodbv2.document.DynamoDB;
//import com.amazonaws.services.dynamodbv2.document.Item;
//import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.UnsubscribeRequest;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Board;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Course;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Professor;
import com.csye6225.fall2018.project.jw.cloudProject.service.BoardService;
import com.csye6225.fall2018.project.jw.cloudProject.service.CourseService;
import com.csye6225.fall2018.project.jw.cloudProject.service.ProfessorService;

public class lambdas implements RequestHandler<DynamodbEvent, String> {
	
	//sns
	private static AmazonSNS sns = AmazonSNSClientBuilder.standard()
			.withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
			.withRegion(Regions.US_EAST_2).build();
	
	@Override
	public String handleRequest(DynamodbEvent input, Context context) {
		String res = "";
		System.out.println("now i am triggered");
		System.out.println(System.getenv("AWS_CREDENTIAL_PROFILES_FILE"));
		if (input.getRecords() != null) {
			for (DynamodbStreamRecord r : input.getRecords()) {
				if (r== null) {
					continue;
				}
				//System.out.println(res.toString()+ "   the info ");
				String boardId = r.getDynamodb().getNewImage().get("boardId").getS();
				//System.out.println(boardId + "     <<boardID");
				String mail = r.getDynamodb().getNewImage().get("announcementText").getS();
				//System.out.println(sns + "    <<sns");
				Board board = new BoardService().getBoard(boardId);
				//Set<String> courseId = board.getCourseId(); 
				String courseId = board.getCourseId();
				//System.out.println(courseId + "    <<courseid");
				groupSendEmail(courseId, mail);
				}
			}
		return   res;
		}
	
	private void groupSendEmail(String courseId, String mail ) {
		if(courseId == null) {
			System.out.println("no courses in the board");
			return;
		}
		//for(String cid : courseId) {
			Course course = new CourseService().getCourse(courseId);
			if(course == null) {
				System.out.println("empty course set to default");
				course = new Course();
				course.setCourseId("SAMPLE- COURSEID");
				course.setName("SAMPLE-NAME");
				course.setSnsTopic("fake");
			}
			Professor prof = new ProfessorService().getProfessor(course.getProfessorId());
				if(prof == null) {
					System.out.println("empty professor set to default");
					prof = new Professor();
					prof.setFirstName("SAMPLE-FIRSTNAME");
					prof.setLastName("SAMPLE-LASTTNAME");
				}
			
				StringBuilder sb = new StringBuilder();

				String profFName = prof.getFirstName();
				System.out.println(prof.getFirstName()+ "    <<profname");
				String profLName = prof.getLastName();
				String profname = profFName + profLName;
				System.out.println(profname + "prof name" );
				
				sb.append("Announcement: ");
				sb.append("\n");
				sb.append(course.getCourseId());
				sb.append("-");
				sb.append(course.getName());
				sb.append("\n");
				sb.append("Professor ");
				sb.append(profname);
				sb.append("\n");
				sb.append("Message: ");
				sb.append("\n");
				sb.append(mail);
				sb.append("\n");
				sb.append("\n");
				sb.append("\n");
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				sb.append(dateFormat.format(date));
				sb.append("-- End of this message. --");
				System.out.println(sb.toString());
				sendEmail(course.getSnsTopic(), sb.toString());
		//}
	}
	 

 
	
	private void sendEmail(String arn, String message) {
		PublishRequest publishRequest = new PublishRequest(arn, message);
		sns.publish(publishRequest);
	}

	public String createARN(String courseId) {//important !!! test it
		CreateTopicRequest createTopicRequest = new CreateTopicRequest(courseId);
		CreateTopicResult createTopicResult = sns.createTopic(createTopicRequest);
		String Arn = createTopicResult.getTopicArn();
		//System.out.println("--> " + Arn);
		return Arn;
	}
	
	public void delTopic(String Arn) {
		DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(Arn);
		sns.deleteTopic(deleteTopicRequest);
	}

	public void subscribe(String Arn, String email) {//key ,test it
		sns.subscribe(new SubscribeRequest(Arn, "email", email));
	}
	
	public void UnSubscribe(String Arn) {//drop class
		sns.unsubscribe(new UnsubscribeRequest(Arn));
	}
	
	
}
