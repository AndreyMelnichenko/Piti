package ApiTests;


import ResponseMessages.EditUserRK;
import ResponseMessages.EditUserRS;
import ResponseMessages.UserRS;
import UserData.UserSingUp;
import core.ApiTestBase;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utils.PropertiesCache.getProperty;


@Epic("API tests")
public class RefreshToken extends PitiApiTest {
    private static String token;

    @Test(priority = 1)
    public void EditUser(){
        UserSingUp expectedUser = new UserSingUp(getProperty("user.email"),getProperty("user.password"));
        UserRS actualUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(expectedUser)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/sign-in")
                .thenReturn().as(UserRS.class);
        System.out.println(actualUser.getResult().getAuth_token());
        System.out.println(actualUser.getResult().getRefresh_token());
        token=actualUser.getResult().getAuth_token();
        System.out.println(token);

        EditUserRK editUserRK = new EditUserRK("+7");
        EditUserRS editUserRS = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(editUserRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/edit-user?id="+getProperty("user.id"))
                .thenReturn().as(EditUserRS.class);
        assertEquals(editUserRS.getSuccess(), "true");
        assertEquals(editUserRS.getResult(), "true");

        try {
            Thread.sleep(301000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        EditUserRS editUserRS2 = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(editUserRK)
                .expect().statusCode(401)
                .when()
                .post(baseURL+"users/edit-user?id="+getProperty("user.id"))
                .thenReturn().as(EditUserRS.class);
        assertEquals(editUserRS2.getSuccess(), "false");
        assertEquals(editUserRS2.getEditUserError().getMessage(),"Your request was made with invalid credentials.");
    }
}
