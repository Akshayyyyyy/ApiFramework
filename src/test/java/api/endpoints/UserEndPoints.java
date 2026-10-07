package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//Here We Do CRUD operation in user end points

public class UserEndPoints {
	
	//Create User
	public static Response createUser(User payload) {

		Response res = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(Routes.post_Url);

		return res;
	}

	//Read User
	public static Response readUser(String userName) {
		
		Response res = given()
				.pathParam("username", userName)
			.when()
				.get(Routes.get_Url);
		
		return res;
	}
	
	//Update User
	public static Response updateUser(String userName, User payload) {
		
		Response res = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
					.pathParam("username", userName)
				.when()
				.put(Routes.put_Url);
		
		return res;
	}
	
	//Delete User
	public static Response deleteUser(String userName) {
		
		Response res = given()
					.pathParam("username", userName)
				.when()
				.delete(Routes.delete_Url);
		
		return res;
	}
	
	
	
	
	
	
}
