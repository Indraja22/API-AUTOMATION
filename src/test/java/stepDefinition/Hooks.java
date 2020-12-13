package stepDefinition;

import io.cucumber.java.Before;

public class Hooks {
	
	StepDefinition sd = new StepDefinition();
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		
		//write a code that will give you place_id
		//execute this code only when place_id is null
		if(StepDefinition.place_id == null) {
		sd.add_place_payload_with("Naik", "Spanish", "One Circus Square,SP");
		sd.user_calls_with_post_http_request("ADD_PLACE_API", "POST");
		sd.verify_place_id_created_that_maps_to_using("Naik", "GET_PLACE_API");
		}
	}

}
