package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before
    public void s(){
        System.out.println("before");
    }
    @Before("@DeletePlace")
    public static void beforeDeletePlaceAPI() throws IOException {
        System.out.println("before delete");
        StepDefinitions sd = new StepDefinitions();
        if(StepDefinitions.place_id==null){
            sd.add_place_payload_with("Dipanshu House","English","23rd Jump Street");
            sd.user_calls_using_with_post_http_request("addPlaceAPI","post");
            sd.verify_created_maps_to_using_get_place_api("place_id","Dipanshu House");
        }
    }
    @After
    public void ag(){
        System.out.println("after");
    }
    @After("@DeletePlace")
    public void as(){
        System.out.println("after delete");
    }
}
