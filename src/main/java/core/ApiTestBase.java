package core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class ApiTestBase {
    protected RequestSpecification spec;
    private ObjectMapper objectMapper = new ObjectMapper();
    protected String baseURL = "https://a2.chis.kiev.ua/api/web/v1/";
    protected static String token, uid, email, password, event_id, firebaseToken, deviceGroup_id;

    @BeforeClass
    public void setUp(){

        spec = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    protected <T> T postSingIn(String url, int statusCode, Class<T> responseClass, Object bodyObject){
        return RestAssured.given()
                .spec(spec)
                .body(bodyObject)
                .expect()
                .statusCode(statusCode)
                .when()
                .post(url)
                .thenReturn().as(responseClass);
    }



    protected <T> T getResource(String location, int statusCode, Class<T> responseClass){
        return RestAssured.given().spec(spec)
                .expect()
                .statusCode(statusCode)
                .when()
                .get(location)
                .thenReturn().as(responseClass);
    }

    protected <T> T postResource(String url, int statusCode, String token, Class<T> responseClass, Object body){
        return RestAssured.given()
                .header("Authorization", "Bearer "+token)
                .spec(spec)
                .body(body)
                .expect()
                .statusCode(statusCode)
                .when()
                .post(url)
                .thenReturn().as(responseClass);
    }
}