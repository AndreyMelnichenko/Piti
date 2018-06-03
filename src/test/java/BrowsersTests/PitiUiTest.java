package BrowsersTests;

import Pages.AccountSettingsPage;
import Pages.LoginPage;
import Pages.RegistrationPage;
import Pages.UserHomePage;
import core.WebDriverTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utils.DataProperties;
import utils.dbClearUser;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class PitiUiTest extends WebDriverTestBase {

    @Test
    public void OpenSingUp() {
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        assertTrue(loginPage.isLogoExists());
        assertEquals(DataProperties.dataProperty("data.properties","login.page.title"),loginPage.getTitleText());
        assertTrue(loginPage.getEmail().isDisplayed());
        assertTrue(loginPage.getPass().isDisplayed());
    }

    @Test
    public void Registration(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.getRegistrationLink().click();
        RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        registrationPage.goRegistration();
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        assertTrue(userHomePage.isMap());
        dbClearUser.getClean();
        userHomePage.userMenuClick();
        userHomePage.exitHomePage();
        assertTrue(loginPage.isLogoExists());
    }

    @Test
    public void SingUp(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet();
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        assertTrue(userHomePage.isMap());
        userHomePage.userMenuClick();
        userHomePage.exitHomePage();
        assertTrue(loginPage.isLogoExists());
    }

    @Test
    public void SendInvite(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet();
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        userHomePage.userMenuClick();
        userHomePage.accountSettingsClick();
        AccountSettingsPage settingsPage = PageFactory.initElements(driver, AccountSettingsPage.class);
        settingsPage.sendInvite(driver);
        assertTrue(settingsPage.checkNewUser());
        dbClearUser.getClean();
        settingsPage.goExit();
    }

    @Test
    public void CreateUser(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet();
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        userHomePage.userMenuClick();
        userHomePage.accountSettingsClick();
        AccountSettingsPage settingsPage = PageFactory.initElements(driver, AccountSettingsPage.class);
        settingsPage.createNewUser();
        assertTrue(settingsPage.getCreatedEmail());
        assertTrue(settingsPage.getCreatedName());
        assertTrue(settingsPage.getCreatedPhone());
        dbClearUser.getClean();
        settingsPage.goExit();
    }
}
