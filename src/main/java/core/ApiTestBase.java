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
    protected String baseURL = "http://api.chis.kiev.ua/api/web/v1/";
    //protected String baseURL="http://185.156.41.135/api/web/v1/";

    @BeforeClass
    public void setUp(){

        spec = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}