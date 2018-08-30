package ApiTests;

import ResponseMessages.EditUserRS;
import ResponseMessages.RestoreRS;
import ResponseMessages.UserRS;
import UserData.UserSingUp;
import core.ApiTestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static utils.PropertiesCache.getProperty;

/**
 * created by Andrey Melnichenko at 11:03 28-08-2018
 */

@Epic("API Device tests")
public class ApiDeviceTest extends ApiTestBase {
    //private static String token, uid, email, password, event_id, firebaseToken;

    @Test(priority = 1)
    @Description("Sing-In")
    @Severity(SeverityLevel.CRITICAL)
    public void singIn(){
        UserSingUp autorizationUser = new UserSingUp(getProperty("user.email"),getProperty("user.password"));
        UserRS currentUser = postSingIn(baseURL+"users/sign-in",200,UserRS.class, autorizationUser);
        assertTrue(currentUser.isSuccess());
        assertEquals(getProperty("user.id"), currentUser.getResult().getUid());
        token=currentUser.getResult().getAuth_token();
    }

    @Test(priority = 2)
    @Description("Add Group Device")
    public void addDeviceGroup(){
        String groupName = "Test Group name";
        Map<String, String> deviceGroup = new HashMap<>();
        deviceGroup.put("name",groupName);
        UserRS currentDeviceGroup = postResource(baseURL+"devices/add-group",200,token,UserRS.class, deviceGroup);
        assertTrue(currentDeviceGroup.isSuccess());
        assertEquals(groupName, currentDeviceGroup.getResult().getTitle());
        deviceGroup_id=currentDeviceGroup.getResult().getId();
    }

    @Test(priority = 3)
    @Description("Add Group Device Negative")
    public void addDeviceGroupBadToken(){
        String groupName = "Test Group name";
        Map<String, String> deviceGroup = new HashMap<>();
        deviceGroup.put("name",groupName);
        EditUserRS currentDeviceGroup = postResource(baseURL+"devices/add-group",401,"76rt34r6t34rt34rt",EditUserRS.class, deviceGroup);
        assertFalse(Boolean.parseBoolean(currentDeviceGroup.getSuccess()));
        assertEquals("Unauthorized", currentDeviceGroup.getName());
    }

    @Test(priority = 4)
    @Description("edit Group Device")
    public void editGroupDevice(){
        String groupName = "Test Group name"+new SimpleDateFormat("_dd-MM-yyyy_HH:mm").format(Calendar.getInstance().getTime());
        Map<String, String> deviceGroup = new HashMap<>();
        deviceGroup.put("name",groupName);
        RestoreRS currentDeviceGroup = postResource(baseURL+"devices/edit-group?id="+deviceGroup_id,200,token,RestoreRS.class, deviceGroup);
        assertTrue(currentDeviceGroup.isSuccess());
        assertTrue(currentDeviceGroup.isResult());
    }

    @Test(priority = 5)
    @Description("delete Group Device")
    public void deleteGroupDevice(){
        String groupName = "";
        Map<String, String> deviceGroup = new HashMap<>();
        deviceGroup.put("name",groupName);
        RestoreRS currentDeviceGroup = postResource(baseURL+"devices/remove-group?id="+deviceGroup_id,200,token,RestoreRS.class, deviceGroup);
        assertTrue(currentDeviceGroup.isSuccess());
        assertTrue(currentDeviceGroup.isResult());
    }

    @Test(priority = 6)
    @Description("delete Group Device Negative")
    public void deleteUnExistsGroupDevice(){
        String groupName = "";
        Map<String, String> deviceGroup = new HashMap<>();
        deviceGroup.put("name",groupName);
        EditUserRS currentDeviceGroup = postResource(baseURL+"devices/remove-group?id=1",403,token,EditUserRS.class, deviceGroup);
        assertFalse(Boolean.parseBoolean(currentDeviceGroup.getSuccess()));
        assertEquals("Forbidden", currentDeviceGroup.getName());
    }

    @Test(priority = 7)
    @Description("delete Group Device Negative")
    public void deleteGroupDeviceBadToken(){
        String groupName = "";
        Map<String, String> deviceGroup = new HashMap<>();
        deviceGroup.put("name",groupName);
        EditUserRS currentDeviceGroup = postResource(baseURL+"devices/remove-group?id=1",401,"4587ty4578y4578ty",EditUserRS.class, deviceGroup);
        assertFalse(Boolean.parseBoolean(currentDeviceGroup.getSuccess()));
        assertEquals("Unauthorized", currentDeviceGroup.getName());
    }
}
