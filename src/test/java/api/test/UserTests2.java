package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;

	@BeforeClass
	public void setupData() {
		
		//Create an object for User.class and faker class inorder to call their methods
		faker = new Faker();
		userPayload = new User();
		
		//we create the data here to pass this data in setID name in payload class
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

	}

	//Post User
	@Test(priority = 1)

	//Pass the above generated data in the methods
	//Call UserEndPoints2 method createUser and as the return type is response, store the response
	public void testPostUser() {
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

	//Update User
	@Test(priority = 3)

	public void testUpdateUserByName() {
		// update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

		// Checking data after update
		Response responseAfterupdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

	//Delete User
	@Test(priority = 4)
	public void testDeleteUserByName()

	{
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		}

}