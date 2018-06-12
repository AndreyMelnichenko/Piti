package BrowsersTests;

import Pages.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import core.WebDriverTestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import utils.CustomWait;
import utils.DataProperties;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.AssertJUnit.assertFalse;
import static utils.PropertiesCache.getProperty;

@Epic("UI tests")
public class BasicUserBehaveTest extends WebDriverTestBase {

    private Login login = new Login();
    private HomePage homePage = new HomePage();
    private ErrPage errPage = new ErrPage();
    private Recovery recovery = new Recovery();
    private Registration registration = new Registration();
    private AccountSettings accountSettings = new AccountSettings();

    @Test (description = "Login page")
    @Description("Login page")
    public void logo() {
        open(baseUrl);
        login.logo().shouldBe(Condition.visible);
        AssertJUnit.assertEquals(login.title().getText(),DataProperties.dataProperty("data.properties", "login.page.title"));

    }
    @Test(dependsOnMethods = "logo", description = "Sing-Up Error Messages")
    @Description("Sing-Up Error Messages Validation")
    public void SingUnErrMessages(){
        login.login().setValue("qatest@email.my");
        login.password().setValue("123qwwedsa");
        login.enter().click();
        AssertJUnit.assertEquals(login.errorLogin().getText(), DataProperties.dataProperty("data.properties", "login.wrong.email"));
        AssertJUnit.assertEquals(login.errorPassword().getText(), DataProperties.dataProperty("data.properties", "login.wrong.password"));
    }

    @Test(dependsOnMethods = "SingUnErrMessages", description = "Recovery Password")
    @Description("Recovery Password")
    public void recoveryPassword(){
        login.recoverPass().shouldBe(Condition.visible).click();
        recovery.title().should(Condition.matchesText(DataProperties.dataProperty("data.properties","recovery.page.title")));
        recovery.emailField().shouldBe(Condition.visible).setValue(getProperty("user.email"));
        recovery.recoveryButton().shouldBe(Condition.visible).click();

    }
    @Test(dependsOnMethods = "recoveryPassword", description = "Re-SingIn")
    @Description("Re-SingIn")
    public void badRegistration(){
        open(baseUrl);
        login.registration().shouldBe(Condition.visible).click();
        registration.emailField().shouldBe(Condition.visible).setValue(getProperty("user.email"));
        registration.passwordField().shouldBe(Condition.visible).setValue(getProperty("user.password"));
        registration.passwordConfirmField().shouldBe(Condition.visible).setValue(getProperty("user.password"));
        registration.buttonCreate().shouldBe(Condition.visible).click();
        registration.errorMessage().shouldBe(Condition.visible).should(Condition.matchesText("Электронная почта \"dima.laktionov5@gmail.com\" has already been taken."));
    }

    @Test(dependsOnMethods = "badRegistration", description = "SingIn")
    @Description("Sing In")
    public void registration(){
        open(baseUrl);
        login.registration().shouldBe(Condition.visible).click();
        registration.emailField().shouldBe(Condition.visible).setValue(getProperty("new.user.email"));
        registration.passwordField().shouldBe(Condition.visible).setValue(getProperty("new.user.password"));
        registration.passwordConfirmField().shouldBe(Condition.visible).setValue(getProperty("new.user.password"));
        registration.buttonCreate().shouldBe(Condition.visible).click();
        CustomWait.getTwoSecondWait();
        CustomWait.getTwoSecondWait();
        homePage.map().waitUntil(Condition.visible,5000);
        homePage.menu().shouldBe(Condition.visible).click();
        homePage.exit().shouldBe(Condition.visible).click();
        login.logo().waitUntil(Condition.visible, 6000);//shouldBe(Condition.visible);
    }

    @Test (dependsOnMethods = "registration", description = "Sing Up")
    @Description("Sing-Up")
    public void enterPersonalCabinet(){
        open(baseUrl);
        login.login().setValue(getProperty("user.email"));
        login.password().setValue(getProperty("user.password"));
        login.enter().click();
        homePage.map().shouldBe(Condition.visible);
    }



    @Test(dependsOnMethods = "enterPersonalCabinet", description = "404 page")
    @Description("404 page")
    public void errorPage(){
        open(baseUrl+"/eeuheirf");
        AssertJUnit.assertEquals(errPage.errTitle().getText(), "404");
        $(By.xpath("//a")).click();
        homePage.map().shouldBe(Condition.visible);
    }

    @Test(dependsOnMethods = "errorPage", description = "Add New User")
    @Description("Add New User")
    public void addUser(){
        homePage.menu().shouldBe(Condition.visible).click();
        homePage.accountSettings().shouldBe(Condition.visible).click();
        accountSettings.createNewUserButton().shouldBe(Condition.visible).click();
        accountSettings.emailNewUser().shouldBe(Condition.visible).setValue(getProperty("new.user.email"));
        accountSettings.nameNewUser().shouldBe(Condition.visible).setValue(getProperty("new.user.fio"));
        accountSettings.passNewUser().shouldBe(Condition.visible).setValue(getProperty("new.user.password"));
        accountSettings.passNewUserConfirm().shouldBe(Condition.visible).setValue(getProperty("new.user.password"));
        accountSettings.phoneNewUser().shouldBe(Condition.visible).setValue(getProperty("new.user.phone"));
        accountSettings.roleNewUser().shouldBe(Condition.visible).click();
        accountSettings.acceptCreateNewUser().shouldBe(Condition.visible).click();
        CustomWait.getTwoSecondWait();
        CustomWait.getOneSecondWait();
        Selenide.refresh();
        accountSettings.mainArea().waitUntil(Condition.visible,5000);
        accountSettings.createdUserEmail().should(Condition.matchesText(getProperty("new.user.email")));
        accountSettings.createdUserName().should(Condition.matchesText(getProperty("new.user.fio")));
        accountSettings.createdUserPhone().should(Condition.matchesText(getProperty("new.user.phone")));
    }

    @Test(dependsOnMethods = "addUser", description = "Send Invite")
    @Description("Send Invite")
    public void invitetoUser(){
        accountSettings.inviteButton().shouldBe(Condition.visible).click();
        accountSettings.emailForImvite().shouldBe(Condition.visible).setValue(getProperty("user.gmail"));
        accountSettings.messageForInvite().shouldBe(Condition.visible).setValue("Welcome to PIT Service");
        accountSettings.simpleRoleInvite().shouldBe(Condition.visible).click();
        accountSettings.acceptSendInvite().shouldBe(Condition.visible).click();
        CustomWait.getOneSecondWait();
        Selenide.refresh();
        accountSettings.mainArea().waitUntil(Condition.visible,2000);
    }

    @Test(dependsOnMethods = "invitetoUser", description = "User change info")
    @Description("User change info")
    public void userChangeInfo(){
        String oldName = accountSettings.firstUserName().getText();
        accountSettings.firstUserThreeDots().shouldBe(Condition.visible).click();
        accountSettings.firstUserEdit().shouldBe(Condition.visible).click();
        accountSettings.fitstUserOldEmail().should(Condition.visible).setValue(getProperty("user.email"));
        String newName = "Dima" + new SimpleDateFormat("_dd-MM-yyyy_HH:mm").format(Calendar.getInstance().getTime());
        accountSettings.firstUserOldName().should(Condition.visible).setValue(newName);
        accountSettings.firstUserOldPhone().should(Condition.visible).setValue(getProperty("new.user.phone"));
        accountSettings.firstUserTimeZone().should(Condition.visible).click();
        CustomWait.getMinWait();
        accountSettings.firstUserTimeZone().should(Condition.visible).click();
        accountSettings.firstUserAcceptNewInfo().should(Condition.visible).click();
        accountSettings.mainArea().waitUntil(Condition.visible, 2000);
        assertFalse(oldName.equals(accountSettings.firstUserName()));

    }

    @Test(dependsOnMethods = "userChangeInfo", description = "Add new Device TK-116")
    @Description("Add new Device TK-116")
    public void addDevice(){
        accountSettings.devicesButton().should(Condition.visible).click();
        accountSettings.addDeviceButton().should(Condition.visible).click();
        accountSettings.newDeviceName().setValue(DataProperties.dataProperty("data.properties","TK116.name"));
        accountSettings.newDeviceImei().setValue(DataProperties.dataProperty("data.properties","TK116.imei"));
        Select selectDevice = new Select(accountSettings.newDeviceType());
        selectDevice.selectByVisibleText(DataProperties.dataProperty("data.properties","TK116.types"));
        accountSettings.newDevicePhone().setValue(DataProperties.dataProperty("data.properties","TK116.sim"));
        accountSettings.newDevicePass().setValue(DataProperties.dataProperty("data.properties","TK116.pass"));
        accountSettings.newDeviceShowPass().click();
        accountSettings.newDeviceApn().setValue(DataProperties.dataProperty("data.properties","TK116.apn"));
        accountSettings.newDeviceAccept().should(Condition.visible).click();
        CustomWait.getTwoSecondWait();
        CustomWait.getTwoSecondWait();
        Selenide.refresh();
        accountSettings.newDeviceItem().waitUntil(Condition.visible,5000);
    }

    @Test(dependsOnMethods = "addDevice", description = "Remove device")
    @Description("Remove device")
    public void removeDevice(){
        accountSettings.mainArea().waitUntil(Condition.visible, 2000);
        accountSettings.removeNewDevice().waitUntil(Condition.visible,2000).click();
        accountSettings.removeNewDeviceButton().waitUntil(Condition.visible,2000).click();
        accountSettings.removeNewDeviceConfirm().waitUntil(Condition.visible,2000).click();
        accountSettings.mainArea().waitUntil(Condition.visible, 2000);
        Selenide.refresh();
        accountSettings.contentField().should(Condition.matchesText(""));
    }

    @Test(dependsOnMethods = "removeDevice", description = "Exit from personal cabinet")
    @Description("Exit from personal cabinet")
    public void exitPersonalCabinet(){
        accountSettings.menuButton().should(Condition.visible).click();
        accountSettings.exitButton().shouldBe(Condition.visible).click();
        login.logo().waitUntil(Condition.visible, 2000);
    }
}
