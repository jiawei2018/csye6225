package aws;

import javax.print.DocFlavor.STRING;

import net.sf.json.JSONObject;

public class testapigateway {
	
	public static void main(String[] args) {
		POSTHelper ph = new POSTHelper();
		JSONObject objout = new JSONObject();
	 
		StringBuilder sb = new StringBuilder();
		sb.append("\"").append("{").append("\"").append("who").append("\"").append(" : \"")
		.append("i call you").append("\"").append("}").append("\"");//now successed......suck format
		//System.out.println(sb.toString());
		
		String stepf =  "arn:aws:states:us-east-2:705018345186:stateMachine:test002"; 
		
		objout.put("input", sb.toString());
		objout.put("stateMachineArn", stepf);
		
		String url1 = "https://g4r9jp56f5.execute-api.us-east-2.amazonaws.com/alpha/execution";
		
		//System.out.println(objout.toString());
		System.out.println(ph.doJsonPost(url1, objout.toString()));
		//ph.doJsonPost(url1, objout.toString());
		
	}
}
