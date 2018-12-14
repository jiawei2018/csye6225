package aws;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Board;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Course;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Register;
import com.csye6225.fall2018.project.jw.cloudProject.service.CourseService;
import net.sf.json.JSONObject;

public class AddToRegisterAndBoard implements RequestHandler<JSONObject, String> {

	@Override
	public String handleRequest(JSONObject input, Context context) {
		context.getLogger().log("Received event: " + input);
		if (input.containsKey("courseId")) {
			String courseId = input.getString("courseId");
			System.out.println(courseId);
			startProc(courseId);
		} else {
			System.out.println("not found! end flow");
		}
		// Get the object from the event and show its content type
		return "i now know how to get event from step function in java";
	}

	private void startProc(String courseId) {
		addRegister(courseId);
		addCourseSns(courseId);
		addBoard(courseId);
	}

	private CourseService css = new CourseService();
	private POSTHelper ph = new POSTHelper();

	public void addRegister(String courseId) {// use post http to build this one
		Register newRegister = new Register();
		newRegister.setOfferingId(courseId);
		newRegister.setOfferType("Course");
		newRegister.setPerUnitPrice(String.valueOf(getPrice(courseId)));
		ph.postRegister(ph.toJsonObj(newRegister));
		// postRegister(toJsonObj(newRegister));
	}

	private int getPrice(String courseId) {
		// some logic here
		int res = 0;
		Course course = css.getCourse(courseId);
		if (course.getDepartment() != null) {
			if (course.getDepartment().equals("csye")) {
				return 6001;// example
			} else if (course.getDepartment().equals("info")) {
				return 6002;// example
			} else if (course.getDepartment().equals("encp")) {
				return 1500;
			}
		}

		return res == 0 ? 6500 : res;
	}

	public void addCourseSns(String courseId) {
		Course course = css.getCourse(courseId);
		// set ARN for the course
		course.setSnsTopic(createARN(course.getName()));
		css.updateCourseInformation(courseId, course);
	}

	public void addBoard(String courseId) {
		Board newBoard = new Board();
		newBoard.setCourseId(courseId);
		newBoard.setOtherInfo("build from lambda");
		System.out.println(newBoard);
		ph.postBoard(ph.toJsonObj(newBoard));
	}

	private String createARN(String courseInfo) {// passed test
		AmazonSNS sns = AmazonSNSClientBuilder.standard()
				.withCredentials(DefaultAWSCredentialsProviderChain.getInstance()).withRegion(Regions.US_EAST_2)
				.build();
		CreateTopicRequest createTopicRequest = new CreateTopicRequest(courseInfo);
		CreateTopicResult createTopicResult = sns.createTopic(createTopicRequest);
		String Arn = createTopicResult.getTopicArn();
		return Arn;
	}

//	public static void main(String[] args) {
//		AddToRegisterAndBoard test = new AddToRegisterAndBoard();
//		Register r1 = new Register();
//		r1.setDepartment("nidaye2222222");
//		//r1.setId("0000998");
//		r1.setOfferingId("co314159");
//		r1.setOfferType("dxhsmwqc");
//		r1.setPerUnitPrice("6666688888");
//		
//		JSONObject  j1 = test.ph.toJsonObj(r1);
//		test.ph.postRegister(j1);
//		//test.postRegister(j1);//passed.
//		//String cid = "nilaolaode111111111111";
//		//test.addBoard(cid);//passed
//		System.out.println(test.createARN("mytest") + "   << arn");
//	}

}