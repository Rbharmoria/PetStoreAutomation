package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	static ResourceBundle getURL(){
		
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //Loads properties file
		return routes;
		}
	
	// Made every method public static so that we can call then directly by class name
	// Response is the return type
	public static Response createUser(User payload) {
		
		String post_url = getURL().getString("post_url");
		//given - we send the request
		//Content type and accept are sent as we receive headers in the curl on swagger
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_url);

		return response;
	}

	public static Response readUser(String userName) {
		String get_url = getURL().getString("get_url");
		Response response = given().pathParam("username", userName).when().get(get_url);

		return response;
	}

	public static Response updateUser(String userName, User payload) {
		String update_url = getURL().getString("update_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(payload).when().put(update_url);

		return response;
	}

	public static Response deleteUser(String userName) {
		String delete_url = getURL().getString("delete_ur1");
		Response response = given().pathParam("username", userName).when().delete(delete_url);

		return response;
	}

}
