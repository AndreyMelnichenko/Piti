package ApiTests;

import ResponseMessages.*;
import UserData.*;
import core.ApiTestBase;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.RandomMinMax;
import utils.SingUpParser;
import utils.dbConnect;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;
import static utils.PropertiesCache.getProperty;

@Epic("API tests")
public class PitiApiTest extends ApiTestBase {
    private static String token, uid, email, password, event_id;

    @Test(dataProvider = "Data collection", dataProviderClass = SingUpParser.class, description = "Sing-Up with wrong data", priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void singInSimplePassword(String email, String pass, String confPass, String timeZone, String lang, String validation, String errMessage){
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
    @Description("Sing-Up")
    @Severity(SeverityLevel.CRITICAL)
    public void singUp(){
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
        dbConnect.clearData();
    }

    @Test(priority = 3)
    @Description("Sing-In")
    @Severity(SeverityLevel.CRITICAL)
    public void singIn(){
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

    @Test(dependsOnMethods = "singIn", priority = 4)
    @Description("invite sending")
    public void invite(){
        InviteRK inviteRK = new InviteRK(getProperty("user.gmail"),"invite messages", "1");
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
    }

    @Test(dependsOnMethods = "invite", priority = 5)
    @Description("invite confirm")
    public void inviteConfirm(){
        InviteConfirmRK inviteConfirmRK = new InviteConfirmRK();
        inviteConfirmRK.setPhone("380502102093");
        inviteConfirmRK.setLang("1");
        inviteConfirmRK.setTime_zone("3");
        inviteConfirmRK.setName("companymobox@gmail.com");
        inviteConfirmRK.setPassword("DSD12DA1Aa2");
        inviteConfirmRK.setPasswordConfirm("DSD12DA1Aa2");
        UserRS inviteConfirmRS = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(inviteConfirmRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/invite-success?token="+dbConnect.getInviteToken(getProperty("user.gmail")))
                .thenReturn().as(UserRS.class);
        assertTrue(inviteConfirmRS.isSuccess());
        dbConnect.clearData();
    }


    @Test(dependsOnMethods = "singIn", priority = 6)
    @Description("invite Remove")
    public void inviteRemove(){
        InviteRK inviteRK = new InviteRK(getProperty("user.gmail"),"invite messages", "1");
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

        RestoreRS inviteRemoveRS = given()
                .header("Authorization", "Bearer "+token)
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/invite-delete?id="+inviteRS.getResult().getInvite_id())
                .thenReturn().as(RestoreRS.class);
        assertTrue(inviteRemoveRS.isSuccess());
        assertTrue(inviteRemoveRS.isResult());
    }

    @Test(dependsOnMethods = "singIn", priority = 7)
    @Description("Restore PASSWORD")
    @Severity(SeverityLevel.CRITICAL)
    public void passRestore(){
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

    @Test (priority = 8)
    @Description("Repare PASSWORD FALSE")
    @Severity(SeverityLevel.CRITICAL)
    public void repairPasswordBadToken(){
        UserPassRepareRK repareRK = new UserPassRepareRK();
        repareRK.setToken(dbConnect.getRepareToken("958gh459gh457gh78h"));//getProperty("user.email")));
        repareRK.setPassword(getProperty("user.password"));
        repareRK.setPasswordConfirm(getProperty("user.password"));
        ErrorRS reparePassword = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(repareRK)
                .expect().statusCode(400)
                .when()
                .post(baseURL+"users/repair-password")
                .thenReturn().as(ErrorRS.class);
        assertFalse(reparePassword.isSuccess());
        assertEquals("57", reparePassword.getError().getMessage().getToken());
    }

    @Test (priority = 9)
    @Description("Repare PASSWORD")
    @Severity(SeverityLevel.CRITICAL)
    public void repairPassword(){
        UserPassRepareRK repareRK = new UserPassRepareRK();
        repareRK.setToken(dbConnect.getRepareToken(getProperty("user.email")));
        repareRK.setPassword(getProperty("user.password"));
        repareRK.setPasswordConfirm(getProperty("user.password"));
        UserRS reparePassword = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(repareRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/repair-password")
                .thenReturn().as(UserRS.class);
        assertTrue(reparePassword.isSuccess());
    }

    @Test(dependsOnMethods = "singIn", priority = 10)
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

    @Test(dependsOnMethods = "singIn", priority = 11)
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

    @Test (priority = 12)
    public void addUserNegative(){
        AddUserRK newUser = new AddUserRK();
        newUser.setEmail(getProperty("user.gmail"));
        newUser.setPassword(getProperty("new.user.password"));
        newUser.setName(getProperty("new.user.fio"));
        newUser.setPhone(getProperty("new.user.phone"));
        newUser.setRole(getProperty("new.user.role"));
        ErrorRS addUserRS = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(newUser)
                .expect().statusCode(400)
                .when()
                .post(baseURL+"users/add-user")
                .thenReturn().as(ErrorRS.class);
        assertFalse(addUserRS.isSuccess());
    }

    @Test(priority = 13)
    public void addUserBadToken(){
        AddUserRK newUser = new AddUserRK();
        newUser.setEmail(getProperty("user.gmail"));
        newUser.setPassword(getProperty("new.user.password"));
        newUser.setName(getProperty("new.user.fio"));
        newUser.setPhone(getProperty("new.user.phone"));
        newUser.setRole(getProperty("new.user.role"));
        EditUserRS addUserRS = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+"uhfefeHIHdisufiufhiuf")
                .spec(spec).body(newUser)
                .expect().statusCode(401)
                .when()
                .post(baseURL+"users/add-user")
                .thenReturn().as(EditUserRS.class);
        assertEquals("Your request was made with invalid credentials.", addUserRS.getEditUserError().getMessage());
        assertEquals("Unauthorized", addUserRS.getName());
        assertFalse(Boolean.parseBoolean(addUserRS.getSuccess()));
    }

    @Test(dependsOnMethods = "singIn", priority = 14)
    public void addUser(){
        AddUserRK newUser = new AddUserRK();
        newUser.setEmail(getProperty("user.gmail"));
        newUser.setPassword(getProperty("new.user.password"));
        newUser.setPasswordConfirm(getProperty("new.user.password"));
        newUser.setName(getProperty("new.user.fio"));
        newUser.setPhone(getProperty("new.user.phone"));
        newUser.setRole(getProperty("new.user.role"));
        RestoreRS addUserRS = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(newUser)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/add-user")
                .thenReturn().as(RestoreRS.class);
        assertTrue(addUserRS.isSuccess());
        assertTrue(addUserRS.isResult());
    }

    @Test(dependsOnMethods = "addUser", priority = 15)
    public void activateEmail(){
        UserRS activateEmail = given()
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/activate-email?token="+dbConnect.getUserToken(getProperty("user.gmail")))
                .thenReturn().as(UserRS.class);
        assertTrue(activateEmail.isSuccess());
    }

    @Test(dependsOnMethods = "addUser", priority = 16)
    public void editUser(){
        InviteConfirmRK editUserRK = new InviteConfirmRK();
        editUserRK.setName("New Name");
        editUserRK.setPhone("234242342432342");
        editUserRK.setTime_zone("3");
        RestoreRS editedUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(editUserRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/edit-user?id="+dbConnect.getUserId(getProperty("user.gmail")))
                .thenReturn().as(RestoreRS.class);
        assertTrue(editedUser.isSuccess());
    }

    @Test(dependsOnMethods = "addUser", priority = 17)
    public void editUserNegative(){
        InviteConfirmRK editUserRK = new InviteConfirmRK();
        editUserRK.setName("New Name");
        editUserRK.setPhone("234242342432342");
        editUserRK.setTime_zone("3");
        EditUserRS editedUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token+"54t45t45t")
                .spec(spec).body(editUserRK)
                .expect().statusCode(401)
                .when()
                .post(baseURL+"users/edit-user?id="+dbConnect.getUserId(getProperty("user.gmail")))
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(editedUser.getSuccess()));
        assertEquals(editedUser.getName(),"Unauthorized");
    }

    @Test(dependsOnMethods = "addUser", priority = 18)
    public void editUserBadUrl(){
        InviteConfirmRK editUserRK = new InviteConfirmRK();
        editUserRK.setName("New Name");
        editUserRK.setPhone("234242342432342");
        editUserRK.setTime_zone("3");
        EditUserRS editedUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(editUserRK)
                .expect().statusCode(403)
                .when()
                .post(baseURL+"users/edit-user?id=1")//+dbConnect.getUserId(getProperty("user.gmail")))
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(editedUser.getSuccess()));
        assertEquals(editedUser.getName(),"Forbidden");
    }

    @Test(dependsOnMethods = "addUser", priority = 19)
    public void deleteUserNegative(){
        EditUserRS deleteUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token+"34y457fy457f")
                .spec(spec)
                .expect().statusCode(401)
                .when()
                .post(baseURL+"users/delete-user?id="+dbConnect.getUserId(getProperty("user.gmail")))
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(deleteUser.getSuccess()));
        assertEquals(deleteUser.getName(),"Unauthorized");
    }

    @Test(dependsOnMethods = "addUser", priority = 20)
    public void deleteUserBadUrl(){
        EditUserRS deleteUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec)
                .expect().statusCode(403)
                .when()
                .post(baseURL+"users/delete-user?id=1")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(deleteUser.getSuccess()));
        assertEquals(deleteUser.getName(),"Forbidden");
    }

    @Test(dependsOnMethods = "addUser", priority = 21)
    public void deleteUser(){
        RestoreRS deleteUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/delete-user?id="+dbConnect.getUserId(getProperty("user.gmail")))
                .thenReturn().as(RestoreRS.class);
        assertTrue(deleteUser.isSuccess());
        assertTrue(deleteUser.isResult());
        dbConnect.clearData();
    }

    @Test(dependsOnMethods = "singIn", priority = 22)
    public void eventsLoad(){
        Events events = given()
                .header("Authorization", "Bearer "+token)
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .get(baseURL+"users/events")
                .thenReturn().as(Events.class);
        assertTrue(events.isSuccess());
        event_id=events.getResult().get(0).getId();
    }

    @Test(dependsOnMethods = "singIn", priority = 23)
    public void eventLoadBadToken(){
        EditUserRS events = given()
                .header("Authorization", "Bearer "+token)
                .spec(spec)
                .expect().statusCode(401)
                .when()
                .get(baseURL+"users/events")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(events.getSuccess()));
        assertEquals("Unauthorized", events.getName());
    }

    @Test(dependsOnMethods = "singIn", priority = 24)
    public void eventDeleteBadToken(){
        EventDeleteRK eventDeleteRK = new EventDeleteRK();
        eventDeleteRK.setEid(Arrays.asList("93ru3489ru394ru439","349yr3948r39i90"));
        EditUserRS deleteEvent = given()
                .header("Authorization", "Bearer 00000001020020202")
                .spec(spec)
                .body(eventDeleteRK)
                .expect().statusCode(401)
                .when()
                .post(baseURL+"users/event-delete")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(deleteEvent.getSuccess()));
        assertEquals("Unauthorized", deleteEvent.getName());

    }

    @Test(dependsOnMethods = "singIn", priority = 25)
    public void eventDelete(){
        EventDeleteRK eventDeleteRK = new EventDeleteRK();
        eventDeleteRK.setEid(Arrays.asList("93ru3489ru394ru439","349yr3948r39i90"));
        RestoreRS deleteEvent = given()
                .header("Authorization", "Bearer "+token)
                .spec(spec)
                .body(eventDeleteRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/event-delete")
                .thenReturn().as(RestoreRS.class);
        assertTrue(deleteEvent.isSuccess());
        assertTrue(deleteEvent.isResult());
    }
}
