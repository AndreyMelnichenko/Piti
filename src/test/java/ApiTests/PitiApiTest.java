package ApiTests;

import ResponseMessages.ErrorRS;
import ResponseMessages.InviteRS;
import ResponseMessages.RestoreRS;
import ResponseMessages.UserRS;
import UserData.*;
import core.ApiTestBase;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.SingUpParser;
import utils.dbClearUser;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static utils.PropertiesCache.getProperty;

@Epic("Log-in tests")
public class PitiApiTest extends ApiTestBase {
    private static String token, uid, email, password;

    @Test(dataProvider = "Data collection", dataProviderClass = SingUpParser.class, priority=1, description = "Sing-In with wrong data")
    @Severity(SeverityLevel.CRITICAL)
    public void SingInSimplePassword(String email, String pass, String confPass,String validation, String errMessage){
        UserRK faledUserRK = new UserRK(email, pass,confPass);
        ErrorRS actualAnswer = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(faledUserRK)
                .expect().statusCode(400)
                .when()
                .post(baseURL+"users/sign-up")
                .thenReturn().as(ErrorRS.class);
        assertFalse(actualAnswer.isSuccess());
        switch (validation){
            case "email":
                assertEquals(actualAnswer.getError().getMessage().getEmail(), errMessage);
                break;
            case "password":
                assertEquals(actualAnswer.getError().getMessage().getPassword(), errMessage);
                break;
            case "passwordConfirm":
                assertEquals(actualAnswer.getError().getMessage().getPasswordConfirm(), errMessage);
                break;
        }
    }

    @Test (priority = 2)
    @Description("Sing-In")
    @Severity(SeverityLevel.CRITICAL)
    public void SingIn (){
        UserRK expectedUserRK = new UserRK(getProperty("new.user.email"),getProperty("new.user.password"),getProperty("new.user.password"));
        UserRS actualUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(expectedUserRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/sign-up")
                .thenReturn().as(UserRS.class);

        assertTrue(actualUser.isSuccess());
        token=actualUser.getResult().getAuth_token();
        uid=actualUser.getResult().getUid();
        email=expectedUserRK.getEmail();
        password=expectedUserRK.getPassword();
        dbClearUser.getClean();
    }

    @Test (priority = 3)
    @Description("Sing-Up")
    @Severity(SeverityLevel.CRITICAL)
    public void SingUp(){
        UserSingUp expectedUser = new UserSingUp(getProperty("user.email"),getProperty("user.password"));
        UserRS actualUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(expectedUser)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/sign-in")
                .thenReturn().as(UserRS.class);
        assertTrue(actualUser.isSuccess());
        assertEquals(getProperty("user.id"), actualUser.getResult().getUid());
        token=actualUser.getResult().getAuth_token();
    }

    @Test(priority = 4, dependsOnMethods = {"SingUp"})
    @Description("Invite sending")
    public void Invite(){
        InviteRK inviteRK = new InviteRK(getProperty("user.gmail"),"Invite messages", "1");
        InviteRS inviteRS = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(inviteRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/invite")
                .thenReturn().as(InviteRS.class);
        assertTrue(inviteRS.isSuccess());
        dbClearUser.getClean();
    }

    @Test (priority = 5)
    @Description("Restore PASSWORD")
    @Severity(SeverityLevel.CRITICAL)
    public void PassRestore(){
        UserRK userRK = new UserRK(getProperty("user.email"),getProperty("user.password"),getProperty("user.password"));
        RestoreRS actualAnswer = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(userRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/restore-password")
                .thenReturn().as(RestoreRS.class);
        assertTrue(actualAnswer.isResult());
        assertTrue(actualAnswer.isSuccess());
    }


}
