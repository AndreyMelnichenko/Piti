import UserData.ErrorRS;
import UserData.UserRK;
import UserData.UserRS;
import core.TestBase;
import io.qameta.allure.*;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import utils.SingUpParser;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static utils.PropertiesCache.getProperty;

@Epic("Log-in tests")
public class PitiTest extends TestBase {
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
                .post("http://api.chis.kiev.ua/api/web/v1/users/sign-up")
                .thenReturn().as(ErrorRS.class);
        assertFalse(actualAnswer.isSuccess());
        switch (validation){
            case "email":
                assertTrue(actualAnswer.getError().getMessage().getEmail().equals(errMessage));
                break;
            case "password":
                assertTrue(actualAnswer.getError().getMessage().getPassword().equals(errMessage));
                break;
            case "passwordConfirm":
                assertTrue(actualAnswer.getError().getMessage().getPasswordConfirm().equals(errMessage));
                break;
        }
    }

    @Ignore
    @Test
    public void SingIn (){
        UserRK expectedUserRK = new UserRK("test7@gmail.com","Q1234567q", "Q1234567q");
        UserRS actualUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(expectedUserRK)
                .expect().statusCode(200)
                .when()
                .post("http://api.chis.kiev.ua/api/web/v1/users/sign-up")
                .thenReturn().as(UserRS.class);

        assertTrue(actualUser.isSuccess());
        token=actualUser.getResult().getAuth_token();
        uid=actualUser.getResult().getUid();
        email=expectedUserRK.getEmail();
        password=expectedUserRK.getPassword();
    }

    @Test (priority = 3)
    @Description("Sing-Up")
    @Severity(SeverityLevel.CRITICAL)
    public void SingUp(){
        UserRK expectedUser = new UserRK(getProperty("user.email"),getProperty("user.password"),getProperty("user.password"));
        UserRS actualUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(expectedUser)
                .expect().statusCode(200)
                .when()
                .post("http://api.chis.kiev.ua/api/web/v1/users/sign-in")
                .thenReturn().as(UserRS.class);
        assertTrue(actualUser.isSuccess());
        assertEquals(getProperty("user.id"), actualUser.getResult().getUid());
        token=actualUser.getResult().getAuth_token();
    }

}
