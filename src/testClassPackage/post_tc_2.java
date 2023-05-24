package testClassPackage;

import java.io.IOException;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonFunctionsPackage.API_Common_Function;
import commonFunctionsPackage.Utility_Common_Functions;
import io.restassured.path.json.JsonPath;
import requestRepositoryPackage.post_req_repository;

public class post_tc_2 {
	@Test
		public static void execute() throws IOException {
	
		for(int i=0;i<5;i++)
			{
				String baseURI=post_req_repository.base_URI();
				String requestBody=post_req_repository.post_req_tc2();
				String resource=post_req_repository.post_resource();
			
				int statusCode=API_Common_Function.response_statusCode(baseURI, requestBody, resource);
				System.out.println("Status code is: "+ statusCode + " Created");
				if(statusCode==201)
				{
					String responseBody=API_Common_Function.response_Body(baseURI, requestBody, resource);
					System.out.println(responseBody);
					post_tc_1.validator(responseBody, statusCode, requestBody);
					Utility_Common_Functions.evidenceFileCreator("post_tc_2", requestBody , responseBody);
					break;
				}
			else 
			{
				System.out.println("Correct StatusCode is not found, hence retrying the API");
			}
		}
}

public static void validator(String responseBody,int statusCode,String requestBody) {
	
		//Parse response body and its parameters
		JsonPath jspres=new JsonPath(responseBody);
		String res_name=jspres.getString("name");
		String res_job=jspres.getString("job");
		String res_id=jspres.getString("id");
		String res_createdAt=jspres.getString("createdAt");
		String currentdate=LocalDate.now().toString();

		//parse request body and its parameters
		JsonPath jspreq=new JsonPath ( requestBody);
		String req_name=jspreq.getString("name");
		String req_job=jspreq.getString("job");

		//Validate the response
		Assert.assertEquals(statusCode,201);
		Assert.assertEquals(req_name, res_name);
		Assert.assertEquals(req_job, res_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_createdAt.substring(0,10), currentdate);
	}
}

