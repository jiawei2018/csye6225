package aws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.util.TextUtils;

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Board;
import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Register;

import net.sf.json.JSONObject;

public class POSTHelper {

	  public JSONObject toJsonObj(Register register) {
	    	JSONObject obj = new JSONObject();
	    		obj.put("id", register.getId());
	    		obj.put("offeringId", register.getOfferingId());
	    		obj.put("department", register.getDepartment());
	    		obj.put("offerType",register.getOfferType());
	    		obj.put("perUnitPrice",register.getPerUnitPrice());
	    		System.out.println(obj.toString());
	    	return obj;
	    }
	    
	    public JSONObject toJsonObj(Board board) {
	    	JSONObject obj = new JSONObject();
	    		obj.put("courseId", board.getCourseId());
	    		obj.put("otherInfo",board.getOtherInfo());
	    		System.out.println(obj.toString());
	    	return obj;
	    }
	    
	    public void postRegister(JSONObject obj) {
	    	String url = "http://cloud-env.zknyamy397.us-east-2.elasticbeanstalk.com/webapi/registers";//local
	    	//String url = "";//aws ec2 url
	    	doJsonPost(url, obj.toString());
	    }
	    public void postBoard(JSONObject obj) {//passed test
	    	String url = "http://cloud-env.zknyamy397.us-east-2.elasticbeanstalk.com/webapi/boards";//local
	    	//String url = "";//aws ec2 url
	    	doJsonPost(url, obj.toString());
	    }
	    
	    public void postCostom(JSONObject obj, String url) {
	    	doJsonPost(url, obj.toString());
	    }
	
	
	public  String doJsonPost(String urlPath, String Json) {//passed test
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");

          conn.setRequestProperty("accept","application/json");
            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
            }
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
