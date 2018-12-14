package aws;

import java.util.List;
import java.util.Set;

import javax.print.DocFlavor.STRING;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Course;
import com.csye6225.fall2018.project.jw.cloudProject.service.CourseService;

import net.sf.json.JSONObject;

//for this
/**
 * - Id (Dynamo Db generated) and hash key - courseId - DynamoDbIndexHashKey, a
 * Global Secondary Index (GSI) - professorId - taId - department Notice the
 * below fields are missing. These fields should be empty. - boardId -
 * listOfRegisteredStudents/roster - has student Id list (this is the roster) -
 * notificationTopic // NEW FIELD for storing sns topic.
 * 
 * 
 * Step2: Starting the course creation workflow. The Dynamodb stream for Course
 * table will trigger a lambda. This lambda is essentially the start of a step
 * function workflow. The workflows first step is to determine if the course is
 * new and if resources need to be created for it. A course is new if boardId,
 * listofRegisteredStudents and it’s notificationTopic field are empty. If the
 * course is not new, the workflow stops.
 *
 */
//work version
public class CourseTrigger implements RequestHandler<DynamodbEvent, String> {

	@Override
	public String handleRequest(DynamodbEvent input, Context context) {
		// preProcessor();
		//context.getLogger().log("Received event: " + input);
		System.out.println("Course trigger ?");
		String res = "";
		if (input.getRecords() != null) {
			for (DynamodbStreamRecord r : input.getRecords()) {// from course table,so
				if (r == null) {
					return null;// if null means no result ,goto loop
				}
				
					String cid = r.getDynamodb().getNewImage().get("courseId").getS();
					System.out.println(cid + "        << course id i here");
					CourseService css = new CourseService(); 
					Course curcourse = css.getCourse(cid);
					
					
//					System.out.println(id + "    <<<<  now at here");
 					String boardId = curcourse.getBoardId();
//					System.out.println(boardId + "     <<<boardid" );
 					String snstopic = curcourse.getSnsTopic();
//					System.out.println(snstopic + "     <<<snstopic" );
//					
 					Set<String> roster = curcourse.getRoster();
//					
//					System.out.println(roster + "     <<<roster size" );

					if (boardId == null && snstopic == null && roster == null) {
						String department = curcourse.getDepartment();
						if(department == null || !department.equals("Seminars")) {
							String courseId = cid;
							String stepf =  "arn:aws:states:us-east-2:705018345186:stateMachine:test002"; 
							String url = "https://g4r9jp56f5.execute-api.us-east-2.amazonaws.com/alpha/execution";
							callAPIgateway("courseId", courseId, stepf, url);
							//callAPIgateway("who", courseId, stepf, url);
							return courseId;
						}
					}
			}//end for
		}
		return null;
	}
	
	
	public void callAPIgateway(String key, String courseId, String stepfuncARN, String gatewayURL) {
		POSTHelper ph = new POSTHelper();
		JSONObject objout = new JSONObject();
		StringBuilder sb = new StringBuilder();
		sb.append("\"").append("{").append("\"").append(key).append("\"").append(" : \"")
		.append(courseId).append("\"").append("}").append("\"");//now successed..
		//String stepf =  "arn:aws:states:us-east-2:705018345186:stateMachine:test002"; 
		//String url1 = "https://g4r9jp56f5.execute-api.us-east-2.amazonaws.com/alpha/execution";
		objout.put("input", sb.toString());
		objout.put("stateMachineArn", stepfuncARN);
		//sysout the information about thispost
		System.out.println(ph.doJsonPost(gatewayURL, objout.toString()));
	}

}