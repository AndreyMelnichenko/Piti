package ApiTests;

import ResponseMessages.EditUserRS;
import ResponseMessages.UserRS;
import UserData.UserSingUp;
import core.ApiTestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

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
public class ApiDeviceTests extends ApiTestBase {
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
    public void addDeviceGroup(){
        String groupName = "Test Group name";
        Map<String, String> deviceGroup = new HashMap<>();
        deviceGroup.put("name",groupName);
        UserRS currentUser = postResource(baseURL+"devices/add-group",200,token,UserRS.class, deviceGroup);
        assertTrue(currentUser.isSuccess());
        assertEquals(groupName, currentUser.getResult().getTitle());
    }

    @Test(priority = 3)
    public void addDeviceGroupBadToken(){
        String groupName = "Test Group name";
        Map<String, String> deviceGroup = new HashMap<>();
        deviceGroup.put("name",groupName);
        EditUserRS currentUser = postResource(baseURL+"devices/add-group",401,"76rt34r6t34rt34rt",EditUserRS.class, deviceGroup);
        assertFalse(Boolean.parseBoolean(currentUser.getSuccess()));
        assertEquals("Unauthorized", currentUser.getName());
    }
}
