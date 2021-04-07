package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.AddPlacePojo;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StepDefinitions extends Utils {

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuild testDataBuild = new TestDataBuild();
    JsonPath js;
    public static String place_id;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        AddPlacePojo addPlacePojo = testDataBuild.addPlacePayload(name, language, address);

        reqSpec = given().spec(requestSpecification()).body(addPlacePojo);
        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @When("User calls {string} using with {string} http request")
    public void user_calls_using_with_post_http_request(String apiRequest, String method) {

        APIResources resources = APIResources.valueOf(apiRequest);
        String apiResource = resources.getResource();
        switch (method.toLowerCase()) {
            case "get":
                response = reqSpec.when().get(apiResource);
                break;
            case "post":
                response = reqSpec.when().post(apiResource);
                break;
            case "delete":
                response = reqSpec.when().delete(apiResource);
                break;
            default:
                throw new RuntimeException("invalid method type");
        }

    }

    @Then("API call is successful with status code as {int}")
    public void api_call_is_successful_with_status_code_as(int statusCode) {
        response = response.then().extract().response();
        Assert.assertEquals(response.statusCode(), statusCode);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {
        //String resp = response.asString();
        Assert.assertEquals(getJsonPath(response, key), expectedValue);
    }

    @Then("Verify {string} created maps to {string} using getPlaceAPI")
    public void verify_created_maps_to_using_get_place_api(String placeIdKey, String name) throws IOException {
         place_id = getJsonPath(response,placeIdKey);
        reqSpec = given().spec(requestSpecification()).queryParam(placeIdKey, place_id);
        user_calls_using_with_post_http_request("getPlaceAPI", "get");
        Assert.assertEquals(getJsonPath(response, "name"), name);
    }

    @Given("DeletePlaceAPI Payload")
    public void delete_place_api_payload() throws IOException {
        reqSpec = given().spec(requestSpecification()).body(testDataBuild.deletePlacePayload(place_id));
    }
}
