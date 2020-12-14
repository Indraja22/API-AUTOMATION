package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resource.APIResources;
import resource.TestDataBuild;
import resource.Utils;

public class StepDefinition extends Utils {
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	RequestSpecification res;
	Response respo;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws Throwable {
		System.out.println("MAP API");
		res = given().spec(requestSpecification()).body(data.addplacePayload(name, language, address));
	}

	@When("User Calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String apiResources, String httpReq) throws Throwable {

		APIResources resourcesAPI = APIResources.valueOf(apiResources);

		System.out.println(resourcesAPI.getResource());

		ResponseSpecification respsec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		if (httpReq.equalsIgnoreCase("POST")) {
			respo = res.when().post(resourcesAPI.getResource());
		} else if (httpReq.equalsIgnoreCase("GET")) {
			respo = res.when().get(resourcesAPI.getResource());
		}
		String respoStr = respo.asString();
		System.out.println(respoStr);
	}

	@Then("The API call is Success with Status Code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) throws Throwable {
		assertEquals(respo.getStatusCode(), 200);
		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is_ok(String keyValue, String expected) throws Throwable {

		assertEquals(getJsonPath(respo, keyValue), expected);

	}

	@Then("Verify place_id created that maps to {string} using {string}")
	public void verify_place_id_created_that_maps_to_using(String expectedName, String resource) throws Throwable {
		place_id = getJsonPath(respo, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		System.out.println(place_id);
		user_calls_with_post_http_request(resource, "GET");
		String name = getJsonPath(respo, "name");
		System.out.println("The expected Name is  : "+expectedName);
		assertEquals(expectedName, name);
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(data.deletePlacePayLoad(place_id));
	}

}
