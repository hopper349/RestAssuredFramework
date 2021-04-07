package pojo;

import java.util.ArrayList;
import java.util.List;

public class AddPlacePojo {

    public Location location;
    public int accuracy;
    public String name;
    public String phone_number;
    public String address;
    public List<String> types;
    public String website;
    public String language;

    public class Location{

        public double lat;
        public double lng;
    }


}
