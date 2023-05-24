package commonFunctionsPackage;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;


public class API_Common_Function {
	
	public static int response_statusCode(String baseURI,String requestbody,String resource) {
	
	RestAssured.baseURI=baseURI;
	
	int statusCode=given().header("Content-type","application/json").body(requestbody)
				   .when().post(resource)
				   .then().extract().response().statusCode();
	
	return statusCode;
	}
	
	
	public static String response_Body(String baseURI,String requestbody,String resource) {
	
	RestAssured.baseURI=baseURI;
	
	String responsebody=given().header("Content-type","application/json").body(requestbody)
						.when().post(resource)
						.then().extract().response().asString();
	return responsebody;
    }
}
