package core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class ApiTestBase {
    protected RequestSpecification spec;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeClass
    public void setUp(){
        String baseURL = "https://reqres.in/api/users/4";
        spec = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                //.setContentType(ContentType.XML)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}