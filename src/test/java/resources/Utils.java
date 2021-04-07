package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification res;

    public RequestSpecification requestSpecification() throws IOException {
        if (res == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
            res = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .setBaseUri(getGlobalValue("baseURL"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))                   //Logging in Rest Assured.
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return res;
        }
        return res;
    }

    public String getGlobalValue(String key) throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\global.properties");
        Properties properties = new Properties();
        properties.load(fis);
        return properties.getProperty(key);
    }

    public String getJsonPath(Response response, String key){
        JsonPath js = new JsonPath(response.asString());
        return js.get(key).toString();
    }
}
