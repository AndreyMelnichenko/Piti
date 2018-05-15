import UserData.ErrorRS;
import UserData.UserRK;
import UserData.UserRS;
import core.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class PitiTest extends TestBase {
    private static String token;
    @Test
    public void SingIn (){
        UserRK expectedUserRK = new UserRK("test6@gmail.com","Q1234567q", "Q1234567q");
        UserRS actualUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(expectedUserRK)
                .expect().statusCode(200)
                .when()
                .post("http://api.chis.kiev.ua/api/web/v1/users/sign-up")
                .thenReturn().as(UserRS.class);

        assertTrue(actualUser.isSuccess());

    }
}
