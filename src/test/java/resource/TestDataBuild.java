package resource;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceBody;
import pojo.Location;

public class TestDataBuild {

	AddPlaceBody p = new AddPlaceBody();
	Location l = new Location();

	public AddPlaceBody addplacePayload(String name, String language, String address) {

		p.setAccuracy(50);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setAddress(address);
		List<String> myList = new ArrayList<String>();
		myList.add("Shoe Park");
		myList.add("Shope");
		p.setTypes(myList);
		l.setLat(-45.3699);
		l.setLng(47.321);
		p.setLocation(l);
		return p;
	}
	
	public String deletePlacePayLoad(String place_id) {
		return "{\r\n"
				+ "       \"place_id\": \""+place_id+"\"\r\n"
				+ "}";
	}

}
