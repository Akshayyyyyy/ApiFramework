package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class TestUsers {
	
	Faker fake;
	User userPayLoad;
	
	@BeforeClass
	public void setupData() {
		System.out.println("Before Class execute");
		
		fake = new Faker();
		userPayLoad = new User();
			
		userPayLoad.setId(fake.idNumber().hashCode());
		userPayLoad.setUsername(fake.name().username());
		userPayLoad.setFirstname(fake.name().firstName());
		userPayLoad.setLastname(fake.name().lastName());
		userPayLoad.setEmail(fake.internet().emailAddress());
		userPayLoad.setPassword(fake.internet().password());
		userPayLoad.setPhone(fake.phoneNumber().cellPhone());
		
	}
	
	@Test(priority = 1)
	public void testPostReq() {
		System.out.println("Post Test execute");
		
		Response res = UserEndPoints.createUser(userPayLoad);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void testGetReq() {
		System.out.println("Get Test execute");
		
		Response res = UserEndPoints.readUser(this.userPayLoad.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testPutReq() {
		System.out.println("Put Test execute");
		
		userPayLoad.setFirstname(fake.name().firstName());
		userPayLoad.setLastname(fake.name().lastName());
		userPayLoad.setEmail(fake.internet().emailAddress());
		
		Response res = UserEndPoints.updateUser(this.userPayLoad.getUsername(), userPayLoad);
		res.then().log().body();
		
		 Assert.assertEquals(res.getStatusCode(), 200);
		 
		 testGetReq();
	
	}
	
	@Test(priority = 4)
	public void testDeleteReq() {
		System.out.println("Delete Test execute");
		
		Response res = UserEndPoints.deleteUser(this.userPayLoad.getUsername());
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
	
	
	
}
