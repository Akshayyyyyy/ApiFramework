package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	@Test(priority=1, dataProvider = "getAllData", dataProviderClass = DataProviders.class)
	public void testPostRequest(String id, String uName, String fName, String lName, String email, String pwd, String ph) {
		System.out.println("Post Test execute");
		
		User userPayLoad = new User();
		
		userPayLoad.setId(Integer.valueOf(id)); 
		userPayLoad.setUsername(uName); 
		userPayLoad.setFirstname(fName); 
		userPayLoad.setLastname(lName); 
		userPayLoad.setEmail(email); 
		userPayLoad.setPassword(pwd);
		userPayLoad.setPhone(ph);

		Response res = UserEndPoints.createUser(userPayLoad);
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "getUserNames", dataProviderClass = DataProviders.class)
	public void testGetReq(String uName) {
		System.out.println("Get Test execute");
		
		Response res = UserEndPoints.readUser(uName);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 3, dataProvider = "getUserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String uName) {
		System.out.println("Delete Test execute");
		
		Response res = UserEndPoints.deleteUser(uName);
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
