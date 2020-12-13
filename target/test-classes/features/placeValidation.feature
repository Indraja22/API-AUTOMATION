Feature: Validating Place API 
@AddPlace @Regression
Scenario Outline: 
	Verfiy Whether Place is being successfully added using Add Place API 
	Given Add Place Payload with "<name>" "<language>" "<address>" 
	When User Calls "<APIResource>" with "<requestType>" http request 
	Then The API call is Success with Status Code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP"
	And Verify place_id created that maps to "<name>" using "<APIresource>"
	Examples: 
		|name|language|address|APIResource|requestType|APIresource|
		|Frontline house|English|ONE TMT SQUARE,NY,USA|ADD_PLACE_API|POST|GET_PLACE_API|
#		|Frontline Bank|Spanish|ONE TMTOMN SQUARE,NYU,SPAIN|ADD_PLACE_API|POST|

@DeletePlace @Regression
Scenario Outline: Verify if Delete Place functionality is working
	Given DeletePlace Payload
	When User Calls "<APIResource>" with "<requestType>" http request
	Then The API call is Success with Status Code 200
	And "status" in response body is "OK" 
	Examples:
	|APIResource|requestType|
	|DELETE_PLACE_API|POST|
	