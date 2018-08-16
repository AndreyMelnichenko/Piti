package ApiTests;

import ResponseMessages.*;
import UserData.*;
import core.ApiTestBase;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.RandomMinMax;
import utils.SingUpParser;
import utils.dbClearUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;
import static utils.PropertiesCache.getProperty;

@Epic("API tests")
public class PitiApiTest extends ApiTestBase {
    private static String token, uid, email, password;

    @Test(dataProvider = "Data collection", dataProviderClass = SingUpParser.class, description = "Sing-In with wrong data", priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void SingInSimplePassword(String email, String pass, String confPass,String timeZone,String lang,String validation, String errMessage){
        UserRK faledUserRK = new UserRK(email, pass,confPass, timeZone, lang);
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

    @Test(priority = 2)
    @Description("Sing-In")
    @Severity(SeverityLevel.CRITICAL)
    public void SingIn (){
        UserRK expectedUserRK = new UserRK(getProperty("new.user.email"),getProperty("new.user.password"),getProperty("new.user.password"),getProperty("user.timezone"),getProperty("user.lang"));
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

    @Test//(priority = 3)
    @Description("Sing-Up")
    //@Severity(SeverityLevel.CRITICAL)
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
        //assertEquals(getProperty("user.id"), actualUser.getResult().getUid());
        token=actualUser.getResult().getAuth_token();
    }

    @Test(dependsOnMethods = "SingUp", priority = 4)
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
        assertEquals(inviteRS.getResult().getEmail(),getProperty("user.gmail"));
        dbClearUser.getClean();
    }

    @Test (dependsOnMethods = "SingUp", priority = 5)
    @Description("Restore PASSWORD")
    @Severity(SeverityLevel.CRITICAL)
    public void PassRestore(){
        UserRK userRK = new UserRK(getProperty("user.email"),getProperty("user.password"),getProperty("user.password"),getProperty("user.timezone"),getProperty("user.lang"));
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

    @Test(dependsOnMethods = "SingUp", priority = 6)
    public void userProfileAvatar(){
        UserSettingsAvatarRK userSettingsRK = new UserSettingsAvatarRK();
        userSettingsRK.setEmail(getProperty("user.email"));
        String userName = "Test User"+ new SimpleDateFormat("_dd-MM-yyyy_HH:mm").format(Calendar.getInstance().getTime());
        userSettingsRK.setName(userName);
        String timeZone = Integer.toString(RandomMinMax.Go(1,17));
        userSettingsRK.setTime_zone(timeZone);
        String lang = Integer.toString(RandomMinMax.Go(1,3));
        userSettingsRK.setLang(lang);
        String phone = "38095"+Integer.toString(RandomMinMax.Go(1000001,9999999));
        userSettingsRK.setPhone(phone);
        userSettingsRK.setAvatar(getProperty("avatar1"));

        UserSettingsRS response = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(userSettingsRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/settings")
                .thenReturn().as(UserSettingsRS.class);

        assertEquals("true", response.getSuccess());
        assertNotEquals("null", response.getResult().getAvatar());
        assertEquals(timeZone, response.getResult().getTime_zone());
        assertEquals(userName, response.getResult().getName());
        assertEquals(phone, response.getResult().getPhone());
        assertEquals(lang, response.getResult().getLang());
        assertEquals("0", response.getResult().getIcon());
    }

    @Test(dependsOnMethods = "SingUp", priority = 7)
    public void userProfileIcon(){
        UserSettingsIconRK userSettingsRK = new UserSettingsIconRK();
        userSettingsRK.setEmail(getProperty("user.email"));
        String userName = "Test User"+ new SimpleDateFormat("_dd-MM-yyyy_HH:mm").format(Calendar.getInstance().getTime());
        userSettingsRK.setName(userName);
        String timeZone = Integer.toString(RandomMinMax.Go(1,17));
        userSettingsRK.setTime_zone(timeZone);
        String phone = "38095"+Integer.toString(RandomMinMax.Go(1000001,9999999));
        userSettingsRK.setPhone(phone);
        String lang = Integer.toString(RandomMinMax.Go(1,3));
        userSettingsRK.setLang(lang);
        String icon = Integer.toString(RandomMinMax.Go(1,6));
        userSettingsRK.setIcon(icon);

        UserSettingsRS response = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(userSettingsRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/settings")
                .thenReturn().as(UserSettingsRS.class);
        assertEquals("true", response.getSuccess());
        assertEquals(icon,response.getResult().getIcon());
        assertEquals(timeZone, response.getResult().getTime_zone());
        assertEquals(userName, response.getResult().getName());
        assertEquals(phone, response.getResult().getPhone());
        assertEquals(lang, response.getResult().getLang());
    }
}
