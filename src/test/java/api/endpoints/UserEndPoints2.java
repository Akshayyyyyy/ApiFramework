package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//Here We Do CRUD operation in user end points

public class UserEndPoints2 {
	
	// Method created for getting url from properties file
	public static ResourceBundle getUrl() {
		ResourceBundle rb = ResourceBundle.getBundle("config");
		return rb;
	}
	
	
	
	//Create User
	public static Response createUser(User payload) {
		
		String post_Url = getUrl().getString("post_url");

		Response res = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(post_Url);

		return res;
	}

	//Read User
	public static Response readUser(String userName) {
		
		String get_Url = getUrl().getString("get_url");
		
		Response res = given()
				.pathParam("username", userName)
			.when()
				.get(get_Url);
		
		return res;
	}
	
	//Update User
	public static Response updateUser(String userName, User payload) {
		String update_Url = getUrl().getString("update_url");
		
		Response res = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
					.pathParam("username", userName)
				.when()
				.put(update_Url);
		
		return res;
	}
	
	//Delete User
	public static Response deleteUser(String userName) {
		
		String delete_Url = getUrl().getString("delete_url");
		
		Response res = given()
					.pathParam("username", userName)
				.when()
				.delete(delete_Url);
		
		return res;
	}
	
	
	
	
	
	
}
