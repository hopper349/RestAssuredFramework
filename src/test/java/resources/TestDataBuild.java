package resources;

import pojo.AddPlacePojo;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlacePojo addPlacePayload(String name, String language, String address){

        AddPlacePojo addPlacePojo = new AddPlacePojo();
        addPlacePojo.location = addPlacePojo.new Location();

        addPlacePojo.location.lat =-38.383494;
        addPlacePojo.location.lng =33.427362;
        addPlacePojo.accuracy = 50;
        addPlacePojo.name = name;
        addPlacePojo.address = address;
        addPlacePojo.phone_number = "(+91) 983 893 3937";
        addPlacePojo.website = "http://google.com";
        addPlacePojo.language = language;
        List<String> typesData = new ArrayList<String>();
        typesData.add("shoe park");
        typesData.add("shop");
        addPlacePojo.types=typesData;
        return addPlacePojo;
    }

    public String deletePlacePayload(String placeId){
        return "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}\n";
    }

}
