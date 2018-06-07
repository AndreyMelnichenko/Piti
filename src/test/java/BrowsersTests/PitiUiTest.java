package BrowsersTests;

import Mail.MailLoginPage;
import Mail.MailMainPage;
import Mail.PasswordPage;
import Pages.*;
import core.WebDriverTestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utils.CustomWait;
import utils.DataProperties;
import utils.dbClearUser;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

@Epic("UI tests")
public class PitiUiTest extends WebDriverTestBase {
    private static String user1="user.email";
    private static String pass1="user.password";
    private static String user2="user2.email";
    private static String pass2="user2.password";


    @Test (priority = 1)
    @Description("Stat UI Tests")
    public void WaitForWatcher(){
        driver.get(baseUrl);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test (priority = 2)
    @Description("Sing-Up Error Message Validation")
    public void UiSingUpErr(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinetWithBadAccess();
        assertEquals(loginPage.getLoginErrorMessage().getText(), DataProperties.dataProperty("data.properties","login.wrong.email"));
        assertEquals(loginPage.getPasswordErrorMessage().getText(), DataProperties.dataProperty("data.properties","login.wrong.password"));
    }

    @Test (priority = 3)
    @Description("404 page")
    public void ErrorPageCheck(){
        driver.get(baseUrl+"/sfosfosifjsod");
        ErrorPage errorPage = PageFactory.initElements(driver, ErrorPage.class);
        assertEquals(errorPage.checkGoMainPageLinkResponseCode(),200);
        assertEquals(errorPage.getTitleText(), "404");
    }

    @Test (priority = 4)
    @Description("Recovery password")
    public void RecoveryPass(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goForgetPage();
        RecoverPass recoverPass = PageFactory.initElements(driver, RecoverPass.class);
        assertTrue(recoverPass.isTextTitle());
        recoverPass.inputEmailToRecover();
        recoverPass.clickButton();
        RecoverSuccess recoverSuccess = PageFactory.initElements(driver, RecoverSuccess.class);
        assertTrue(recoverSuccess.checkRecoveredEmail());
    }

    @Test (priority = 5)
    @Description("Sing-In Page")
    public void OpenSingUp() {
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        assertTrue(loginPage.isLogoExists());
        assertEquals(DataProperties.dataProperty("data.properties","login.page.title"),loginPage.getTitleText());
        assertTrue(loginPage.getEmail().isDisplayed());
        assertTrue(loginPage.getPass().isDisplayed());
    }

    @Test (priority = 6)
    @Description("Registration")
    public void Registration(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.getRegistrationLink().click();
        RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        registrationPage.goRegistration();
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        assertTrue(userHomePage.isMap());
        userHomePage.userMenuClick();
        userHomePage.exitHomePage();
        dbClearUser.getClean();
        CustomWait.getOneSecondWait();
        assertTrue(loginPage.isLogoExists());
    }

    @Test (priority = 7)
    @Description("Sing-Up")
    public void SingUp(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet(user1,pass1);
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        assertTrue(userHomePage.isMap());
        userHomePage.userMenuClick();
        userHomePage.exitHomePage();
        assertTrue(loginPage.isLogoExists());
    }

    @Test(priority = 8)
    @Description("Send invite antother User")
    public void SendInvite(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet(user1,pass1);
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        userHomePage.userMenuClick();
        userHomePage.accountSettingsClick();
        AccountSettingsPage settingsPage = PageFactory.initElements(driver, AccountSettingsPage.class);
        settingsPage.sendInvite(driver);
        assertTrue(settingsPage.checkNewUser());
        dbClearUser.getClean();
        settingsPage.goExit();
    }

    @Test(priority = 9)
    @Description("Create New User")
    public void CreateUser(){
        driver.get(baseUrl);
        CustomWait.getOneSecondWait();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet(user1,pass1);
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        userHomePage.userMenuClick();
        userHomePage.accountSettingsClick();
        AccountSettingsPage settingsPage = PageFactory.initElements(driver, AccountSettingsPage.class);
        settingsPage.createNewUser();
        assertTrue(settingsPage.getCreatedEmail());
        assertTrue(settingsPage.getCreatedName());
        assertTrue(settingsPage.getCreatedPhone());
        settingsPage.goExit();
    }

    @Test(priority = 10)
    @Description("Change user info")
    public void UserChageInfo(){
        driver.get(baseUrl);
        CustomWait.getOneSecondWait();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet(user1,pass1);
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        userHomePage.userMenuClick();
        userHomePage.accountSettingsClick();
        AccountSettingsPage settingsPage = PageFactory.initElements(driver, AccountSettingsPage.class);
        assertFalse(settingsPage.changeUserData());
        settingsPage.goExit();
    }

    @Test(priority = 11)
    @Description("Add new device TK-116 to user")
    public void AddDevice(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet(user1,pass1);
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        userHomePage.userMenuClick();
        userHomePage.accountSettingsClick();
        AccountSettingsPage settingsPage = PageFactory.initElements(driver, AccountSettingsPage.class);
        settingsPage.goDevices();
        AccountDevices accountDevices = PageFactory.initElements(driver, AccountDevices.class);
        accountDevices.clickAddDevice();
        assertTrue(accountDevices.isNewDeviceCreated());
        accountDevices.isNewDeviceRemoved();
        CustomWait.getOneSecondWait();
    }

    @Ignore
    @Test(priority = 12)
    @Description("Add new device GT3101 to user")
    public void AddDeviceAnotheUser(){
        driver.get(baseUrl);
        CustomWait.getOneSecondWait();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet(user2,pass2);
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        userHomePage.clickAddNewDevice();
        userHomePage.fillFormGt3101();
    }

    @Test(priority = 13)
    @Description("Chek email notification")
    public void EmailInviteChecker(){
        driver.get(gmail);
        MailLoginPage mailLoginPage = PageFactory.initElements(driver, MailLoginPage.class);
        mailLoginPage.handleSingIn();
        mailLoginPage.goEmail();
        PasswordPage passwordPage = PageFactory.initElements(driver, PasswordPage.class);
        passwordPage.goPassword();
        MailMainPage mailMainPage = PageFactory.initElements(driver, MailMainPage.class);
        mailMainPage.closeAttentionMessage();
        assertTrue(mailMainPage.getEmailTitle());
        mailMainPage.cleanEmailList();
        mailMainPage.alertHndle(driver);
    }
}
