package ApiTests;

import ResponseMessages.*;
import UserData.*;
import core.ApiTestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import utils.RandomMinMax;
import utils.SingUpParser;
import utils.dbConnect;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.testng.Assert.*;
import static utils.PropertiesCache.getProperty;

@Epic("API Users tests")
public class PitiApiTest extends ApiTestBase {
    //private static String token, uid, email, password, event_id, firebaseToken;

    @Test(dataProvider = "Data collection", dataProviderClass = SingUpParser.class, description = "Sing-Up with wrong data", priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void singInSimplePassword(String email, String pass, String confPass, String timeZone, String lang, String validation, String errMessage) {
        UserRK faledUserRK = new UserRK(email, pass, confPass, timeZone, lang);
        ErrorRS actualAnswer = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .spec(spec).body(faledUserRK)
                .expect().statusCode(400)
                .when()
                .post(baseURL + "users/sign-up")
                .thenReturn().as(ErrorRS.class);
        assertFalse(actualAnswer.isSuccess());
        switch (validation) {
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
    public void singUp() {
        UserRK expectedUserRK = new UserRK(getProperty("new.user.email"), getProperty("new.user.password"), getProperty("new.user.password"), getProperty("user.timezone"), getProperty("user.lang"));
        UserRS actualUser = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .spec(spec).body(expectedUserRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/sign-up")
                .thenReturn().as(UserRS.class); //response

        assertTrue(actualUser.isSuccess());
        token = actualUser.getResult().getAuth_token();
        uid = actualUser.getResult().getUid();
        email = expectedUserRK.getEmail();
        password = expectedUserRK.getPassword();
        dbConnect.clearData();
    }

    @Test(priority = 3)
    @Description("Sing-In")
    @Severity(SeverityLevel.CRITICAL)
    public void singIn() {
        UserSingUp expectedUser = new UserSingUp(getProperty("user.email"), getProperty("user.password"));
        UserRS actualUser = postSingIn(baseURL + "users/sign-in", 200, UserRS.class, expectedUser);
        assertTrue(actualUser.isSuccess());
        assertEquals(getProperty("user.id"), actualUser.getResult().getUid());
        token = actualUser.getResult().getAuth_token();
    }

    @Test(priority = 4)
    @Description("invite sending")
    public void invite() {
        Map<String, String> inviteRK = new HashMap<>();
        inviteRK.put("email", getProperty("user.gmail"));
        inviteRK.put("message", "invite messages");
        inviteRK.put("access", "5");
        inviteRK.put("createdeleteusers", "true");
        inviteRK.put("addremovealldevice", "false");
        inviteRK.put("addremoveowndevice", "false");
        InviteRS inviteRS = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .spec(spec).body(inviteRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/invite")
                .thenReturn().as(InviteRS.class);
        assertTrue(inviteRS.isSuccess());
        assertEquals(inviteRS.getResult().getEmail(), getProperty("user.gmail"));
    }

    @Test(dependsOnMethods = "invite", priority = 5)
    @Description("invite confirm")
    public void inviteConfirm() {
        InviteConfirmRK inviteConfirmRK = new InviteConfirmRK();
        inviteConfirmRK.setPhone("380502102093");
        inviteConfirmRK.setLang("1");
        inviteConfirmRK.setTime_zone("5");
        inviteConfirmRK.setName("companymobox@gmail.com");
        inviteConfirmRK.setPassword("DSD12DA1Aa2");
        inviteConfirmRK.setPasswordConfirm("DSD12DA1Aa2");
        UserRS inviteConfirmRS = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .spec(spec).body(inviteConfirmRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/invite-success?token=" + dbConnect.getInviteToken(getProperty("user.gmail")))
                .thenReturn().as(UserRS.class);
        assertTrue(inviteConfirmRS.isSuccess());
        dbConnect.clearData();
    }

    @Test(priority = 6)
    @Description("invite Remove")
    public void inviteRemove() {
        Map<String, String> inviteRK = new HashMap<>();
        inviteRK.put("email", getProperty("user.gmail"));
        inviteRK.put("message", "invite messages");
        inviteRK.put("access", "5");
        inviteRK.put("createdeleteusers", "true");
        inviteRK.put("addremovealldevice", "false");
        inviteRK.put("addremoveowndevice", "false");

        InviteRS inviteRS = postResource(baseURL + "users/invite", 200, token, InviteRS.class, inviteRK);
        assertTrue(inviteRS.isSuccess());
        assertEquals(inviteRS.getResult().getEmail(), getProperty("user.gmail"));

        RestoreRS inviteRemoveRS = postResource(baseURL + "users/invite-delete?id=" + inviteRS.getResult().getInvite_id(), 200, token, RestoreRS.class);
        assertTrue(inviteRemoveRS.isSuccess());
        assertTrue(inviteRemoveRS.isResult());
    }

    @Test(priority = 7)
    @Description("Restore PASSWORD")
    @Severity(SeverityLevel.CRITICAL)
    public void passRestore() {
        UserRK userRK = new UserRK(getProperty("user.email"), getProperty("user.password"), getProperty("user.password"), getProperty("user.timezone"), getProperty("user.lang"));
        RestoreRS actualAnswer = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .spec(spec).body(userRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/restore-password")
                .thenReturn().as(RestoreRS.class);
        assertTrue(actualAnswer.isResult());
        assertTrue(actualAnswer.isSuccess());
    }

    @Test(priority = 8)
    @Description("Repare PASSWORD FALSE")
    @Severity(SeverityLevel.CRITICAL)
    public void repairPasswordBadToken() {
        UserPassRepareRK repareRK = new UserPassRepareRK();
        repareRK.setToken(dbConnect.getRepareToken("958gh459gh457gh78h"));//getProperty("user.email")));
        repareRK.setPassword(getProperty("user.password"));
        repareRK.setPasswordConfirm(getProperty("user.password"));
        ErrorRS reparePassword = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .spec(spec).body(repareRK)
                .expect().statusCode(400)
                .when()
                .post(baseURL + "users/repair-password")
                .thenReturn().as(ErrorRS.class);
        assertFalse(reparePassword.isSuccess());
        assertEquals("57", reparePassword.getError().getMessage().getToken());
    }

    @Test(priority = 9)
    @Description("Repare PASSWORD")
    @Severity(SeverityLevel.CRITICAL)
    public void repairPassword() {
        UserPassRepareRK repareRK = new UserPassRepareRK();
        repareRK.setToken(dbConnect.getRepareToken(getProperty("user.email")));
        repareRK.setPassword(getProperty("user.password"));
        repareRK.setPasswordConfirm(getProperty("user.password"));
        UserRS reparePassword = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .spec(spec).body(repareRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/repair-password")
                .thenReturn().as(UserRS.class);
        assertTrue(reparePassword.isSuccess());
    }

    @Test(priority = 10)
    public void userProfileAvatar() {
        UserSettingsAvatarRK userSettingsRK = new UserSettingsAvatarRK();
        userSettingsRK.setEmail(getProperty("user.email"));
        String userName = "Test User" + new SimpleDateFormat("_dd-MM-yyyy_HH:mm").format(Calendar.getInstance().getTime());
        userSettingsRK.setName(userName);
        String timeZone = Integer.toString(RandomMinMax.Go(1, 17));
        userSettingsRK.setTime_zone(timeZone);
        String lang = Integer.toString(RandomMinMax.Go(1, 3));
        userSettingsRK.setLang(lang);
        String phone = "38095" + Integer.toString(RandomMinMax.Go(1000001, 9999999));
        userSettingsRK.setPhone(phone);
        userSettingsRK.setAvatar(getProperty("avatar1"));

        UserSettingsRS response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .spec(spec).body(userSettingsRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/settings")
                .thenReturn().as(UserSettingsRS.class);

        assertEquals("true", response.getSuccess());
        assertNotEquals("null", response.getResult().getAvatar());
        assertEquals(timeZone, response.getResult().getTime_zone());
        assertEquals(userName, response.getResult().getName());
        assertEquals(phone, response.getResult().getPhone());
        assertEquals(lang, response.getResult().getLang());
        assertEquals("0", response.getResult().getIcon());
    }

    @Test(priority = 11)
    public void userProfileIcon() {
        UserSettingsIconRK userSettingsRK = new UserSettingsIconRK();
        userSettingsRK.setEmail(getProperty("user.email"));
        String userName = "Test User" + new SimpleDateFormat("_dd-MM-yyyy_HH:mm").format(Calendar.getInstance().getTime());
        userSettingsRK.setName(userName);
        String timeZone = Integer.toString(RandomMinMax.Go(1, 17));
        userSettingsRK.setTime_zone(timeZone);
        String phone = "38095" + Integer.toString(RandomMinMax.Go(1000001, 9999999));
        userSettingsRK.setPhone(phone);
        String lang = Integer.toString(RandomMinMax.Go(1, 3));
        userSettingsRK.setLang(lang);
        String icon = Integer.toString(RandomMinMax.Go(1, 6));
        userSettingsRK.setIcon(icon);

        UserSettingsRS response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .spec(spec).body(userSettingsRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/settings")
                .thenReturn().as(UserSettingsRS.class);
        assertEquals("true", response.getSuccess());
        assertEquals(icon, response.getResult().getIcon());
        assertEquals(timeZone, response.getResult().getTime_zone());
        assertEquals(userName, response.getResult().getName());
        assertEquals(phone, response.getResult().getPhone());
        assertEquals(lang, response.getResult().getLang());
    }

    @Test(priority = 12)
    public void addUserNegative() {
        AddUserRK newUser = new AddUserRK();
        newUser.setEmail(getProperty("user.gmail"));
        newUser.setPassword(getProperty("new.user.password"));
        newUser.setName(getProperty("new.user.fio"));
        newUser.setPhone(getProperty("new.user.phone"));
        newUser.setRole(getProperty("new.user.role"));
        ErrorRS addUserRS = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .spec(spec).body(newUser)
                .expect().statusCode(400)
                .when()
                .post(baseURL + "users/add-user")
                .thenReturn().as(ErrorRS.class);
        assertFalse(addUserRS.isSuccess());
    }

    @Test(priority = 13)
    public void addUserBadToken() {
        AddUserRK newUser = new AddUserRK();
        newUser.setEmail(getProperty("user.gmail"));
        newUser.setPassword(getProperty("new.user.password"));
        newUser.setName(getProperty("new.user.fio"));
        newUser.setPhone(getProperty("new.user.phone"));
        newUser.setRole(getProperty("new.user.role"));
        EditUserRS addUserRS = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + "uhfefeHIHdisufiufhiuf")
                .spec(spec).body(newUser)
                .expect().statusCode(401)
                .when()
                .post(baseURL + "users/add-user")
                .thenReturn().as(EditUserRS.class);
        assertEquals("Your request was made with invalid credentials.", addUserRS.getEditUserError().getMessage());
        assertEquals("Unauthorized", addUserRS.getName());
        assertFalse(Boolean.parseBoolean(addUserRS.getSuccess()));
    }

    @Test(priority = 14)
    public void addUser() {
        AddUserRK newUser = new AddUserRK();
        newUser.setEmail(getProperty("user.gmail"));
        newUser.setPassword(getProperty("new.user.password"));
        newUser.setPasswordConfirm(getProperty("new.user.password"));
        newUser.setName(getProperty("new.user.fio"));
        newUser.setPhone(getProperty("new.user.phone"));
        newUser.setRole(getProperty("new.user.role"));
        newUser.setSubunsubdevice("true");
        RestoreRS addUserRS = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .spec(spec).body(newUser)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/add-user")
                .thenReturn().as(RestoreRS.class);
        assertTrue(addUserRS.isSuccess());
        assertTrue(addUserRS.isResult());
    }

    @Test(priority = 15)
    public void getUserAccount() {
        UserListRS getUser = given()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .get(baseURL + "users/get-users")
                .thenReturn().as(UserListRS.class);
        assertTrue(getUser.isSuccess());
        assertEquals(getProperty("new.user.email"), getUser.getResult().get(0).getEmail());
    }

    @Test(priority = 16)
    public void getUserAccountBadToken() {
        EditUserRS getUser = given()
                .header("Authorization", "Bearer 2618612564")
                .spec(spec)
                .expect().statusCode(401)
                .when()
                .get(baseURL + "users/get-users")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(getUser.getSuccess()));
        assertEquals("Unauthorized", getUser.getName());
    }

    @Test(dependsOnMethods = "addUser", priority = 17)
    public void activateEmail() {
        UserRS activateEmail = given()
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/activate-email?token=" + dbConnect.getUserToken(getProperty("user.gmail")))
                .thenReturn().as(UserRS.class);
        assertTrue(activateEmail.isSuccess());
    }

    @Test(priority = 18)
    public void editUser() {
        InviteConfirmRK editUserRK = new InviteConfirmRK();
        editUserRK.setName("New Name");
        editUserRK.setPhone("234242342432342");
        editUserRK.setTime_zone("3");
        editUserRK.setRole("3");
        editUserRK.setSubunsubdevice("true");
        RestoreRS editedUser = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .spec(spec).body(editUserRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/edit-user?id=" + dbConnect.getUserId(getProperty("user.gmail")))
                .thenReturn().as(RestoreRS.class);
        assertTrue(editedUser.isSuccess());
    }

    @Test(dependsOnMethods = "addUser", priority = 19)
    public void editUserNegative() {
        InviteConfirmRK editUserRK = new InviteConfirmRK();
        editUserRK.setName("New Name");
        editUserRK.setPhone("234242342432342");
        editUserRK.setTime_zone("3");
        EditUserRS editedUser = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token + "54t45t45t")
                .spec(spec).body(editUserRK)
                .expect().statusCode(401)
                .when()
                .post(baseURL + "users/edit-user?id=" + dbConnect.getUserId(getProperty("user.gmail")))
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(editedUser.getSuccess()));
        assertEquals(editedUser.getName(), "Unauthorized");
    }

    @Test(priority = 20)
    public void editUserBadUrl() {
        InviteConfirmRK editUserRK = new InviteConfirmRK();
        editUserRK.setName("New Name");
        editUserRK.setPhone("234242342432342");
        editUserRK.setTime_zone("3");
        EditUserRS editedUser = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .spec(spec).body(editUserRK)
                .expect().statusCode(403)
                .when()
                .post(baseURL + "users/edit-user?id=1")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(editedUser.getSuccess()));
        assertEquals(editedUser.getName(), "Forbidden");
    }

    @Test(priority = 21)
    public void deleteUserNegative() {
        EditUserRS deleteUser = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token + "34y457fy457f")
                .spec(spec)
                .expect().statusCode(401)
                .when()
                .post(baseURL + "users/delete-user?id=" + dbConnect.getUserId(getProperty("user.gmail")))
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(deleteUser.getSuccess()));
        assertEquals(deleteUser.getName(), "Unauthorized");
    }

    @Test(priority = 22)
    public void deleteUserBadUrl() {
        EditUserRS deleteUser = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .expect().statusCode(403)
                .when()
                .post(baseURL + "users/delete-user?id=1")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(deleteUser.getSuccess()));
        assertEquals(deleteUser.getName(), "Forbidden");
    }

    @Test(priority = 23)
    public void deleteUser() {
        RestoreRS deleteUser = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/delete-user?id=" + dbConnect.getUserId(getProperty("user.gmail")))
                .thenReturn().as(RestoreRS.class);
        assertTrue(deleteUser.isSuccess());
        assertTrue(deleteUser.isResult());
        dbConnect.clearData();
    }

    @Test(priority = 24)
    public void eventsLoad() {
        UserSingUp autorizationUser = new UserSingUp(getProperty("user.email"), getProperty("user.password"));
        UserRS currentUser = postSingIn(baseURL + "users/sign-in", 200, UserRS.class, autorizationUser);
        token = currentUser.getResult().getAuth_token();

        Events events = given()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .get(baseURL + "users/events")
                .thenReturn().as(Events.class);
        assertTrue(events.isSuccess());
        event_id = events.getResult().get(0).getId();
    }

    @Test(priority = 25)
    public void eventLoadBadToken() {
        EditUserRS events = given()
                .header("Authorization", "Bearer 712121212121212")
                .spec(spec)
                .expect().statusCode(401)
                .when()
                .get(baseURL + "users/events")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(events.getSuccess()));
        assertEquals("Unauthorized", events.getName());
    }

    @Test(priority = 26)
    public void eventDeleteBadToken() {
        EventDeleteRK eventDeleteRK = new EventDeleteRK();
        eventDeleteRK.setEid(Arrays.asList("93ru3489ru394ru439", "349yr3948r39i90"));
        EditUserRS deleteEvent = given()
                .header("Authorization", "Bearer 00000001020020202")
                .spec(spec)
                .body(eventDeleteRK)
                .expect().statusCode(401)
                .when()
                .post(baseURL + "users/event-delete")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(deleteEvent.getSuccess()));
        assertEquals("Unauthorized", deleteEvent.getName());

    }

    @Test(priority = 27)
    public void eventDelete() {
        EventDeleteRK eventDeleteRK = new EventDeleteRK();
        eventDeleteRK.setEid(Arrays.asList("93ru3489ru394ru439", "349yr3948r39i90"));
        RestoreRS deleteEvent = given()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .body(eventDeleteRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/event-delete")
                .thenReturn().as(RestoreRS.class);
        assertTrue(deleteEvent.isSuccess());
        assertTrue(deleteEvent.isResult());
    }

    @Test(priority = 28)
    public void userList() {
        UserListRS userListRS = given()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .get(baseURL + "users/list")
                .thenReturn().as(UserListRS.class);
        assertTrue(userListRS.isSuccess());
    }

    @Test(priority = 29)
    public void userListBadToken() {
        EditUserRS userListRS = given()
                .header("Authorization", "Bearer 0000000000000000")
                .spec(spec)
                .expect().statusCode(401)
                .when()
                .get(baseURL + "users/list")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(userListRS.getSuccess()));
        assertEquals("Unauthorized", userListRS.getName());
    }

    @Test(priority = 30)
    public void userSessions() {
        UserRS usersSessions = given()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .get(baseURL + "users/sessions")
                .thenReturn().as(UserRS.class);
        assertTrue(usersSessions.isSuccess());
    }

    @Test(priority = 31)
    public void userSessionsBadToken() {
        EditUserRS usersSessions = given()
                .header("Authorization", "Bearer 1231312312311")
                .spec(spec)
                .expect().statusCode(401)
                .when()
                .get(baseURL + "users/sessions")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(usersSessions.getSuccess()));
        assertEquals("Unauthorized", usersSessions.getName());
    }

    @Test(priority = 32)
    public void fireBaseTokenAdd() {
        firebaseToken = "98ht5948gt4589ghg";
        Map<String, String> fireBaseToken = new HashMap<>();
        fireBaseToken.put("token", firebaseToken);
        RestoreRS fireBaseTokenRK = given()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .body(fireBaseToken)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/fire-token-save")
                .thenReturn().as(RestoreRS.class);
        assertTrue(fireBaseTokenRK.isSuccess());
        assertEquals(firebaseToken, dbConnect.getFireBaseToken(getProperty("user.email")));
    }

    @Test(priority = 33)
    public void fireBaseTokenCheck() {
        UserRS fireBaseToken = given()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .get(baseURL + "users/fire-check")
                .thenReturn().as(UserRS.class);
        assertTrue(fireBaseToken.isSuccess());
        assertTrue(Boolean.parseBoolean(fireBaseToken.getResult().getFirebase()));
    }

    @Test(priority = 34)
    public void fireBaseBadTokenCheck() {
        EditUserRS fireBaseToken = given()
                .header("Authorization", "Bearer ")
                .spec(spec)
                .expect().statusCode(401)
                .when()
                .get(baseURL + "users/fire-check")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(fireBaseToken.getSuccess()));
        assertEquals(fireBaseToken.getName(), "Unauthorized");
    }

    @Test(priority = 35)
    public void fireBaseBadTokenAdd() {
        firebaseToken = "98ht5948gt4589ghg";
        Map<String, String> fireBaseToken = new HashMap<>();
        fireBaseToken.put("token", firebaseToken);
        EditUserRS fireBaseBadTokenRK = given()
                .header("Authorization", "Bearer 348534538956734895634")
                .spec(spec)
                .body(fireBaseToken)
                .expect().statusCode(401)
                .when()
                .post(baseURL + "users/fire-token-save")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(fireBaseBadTokenRK.getSuccess()));
        assertEquals("Unauthorized", fireBaseBadTokenRK.getName());
    }

    @Test(priority = 36)
    public void fireBaseTokenDelete() {
        RestoreRS fireBaseTokenDeleteRK = given()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .post(baseURL + "users/fire-token-delete")
                .thenReturn().as(RestoreRS.class);
        assertTrue(fireBaseTokenDeleteRK.isSuccess());
        assertTrue(fireBaseTokenDeleteRK.isResult());
    }

    @Test(priority = 37)
    public void fireBaseBadTokenDelete() {
        EditUserRS fireBaseTokenDeleteRK = given()
                .header("Authorization", "Bearer 3985395398573489")
                .spec(spec)
                .expect().statusCode(401)
                .when()
                .post(baseURL + "users/fire-token-delete")
                .thenReturn().as(EditUserRS.class);
        assertFalse(Boolean.parseBoolean(fireBaseTokenDeleteRK.getSuccess()));
        assertEquals("Unauthorized", fireBaseTokenDeleteRK.getName());
    }

    @Test(priority = 38)
    public void userSettings() {
        UserRS userSettings = given()
                .header("Authorization", "Bearer " + token)
                .spec(spec)
                .expect().statusCode(200)
                .when()
                .get(baseURL + "users/settings")
                .thenReturn().as(UserRS.class);
        assertTrue(userSettings.isSuccess());
        assertEquals(userSettings.getResult().getEmail(), getProperty("user.email"));
    }
}