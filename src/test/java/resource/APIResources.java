package resource;

public enum APIResources {
	
	ADD_PLACE_API("/maps/api/place/add/json"), DELETE_PLACE_API("/maps/api/place/delete/json"),
	GET_PLACE_API("/maps/api/place/get/json");
	private String resource;
	APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}

}
