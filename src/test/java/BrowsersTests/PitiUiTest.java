package BrowsersTests;

import Pages.*;
import core.WebDriverTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utils.DataProperties;
import utils.dbClearUser;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class PitiUiTest extends WebDriverTestBase {



    @Test (priority = 0)
    public void OpenSingUp() {
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        assertTrue(loginPage.isLogoExists());
        assertEquals(DataProperties.dataProperty("data.properties","login.page.title"),loginPage.getTitleText());
        assertTrue(loginPage.getEmail().isDisplayed());
        assertTrue(loginPage.getPass().isDisplayed());
    }

    @Test (priority = 1)
    public void SingUpErr(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinetWithBadAccess();
        assertEquals(loginPage.getLoginErrorMessage().getText(), DataProperties.dataProperty("data.properties","login.wrong.email"));
        assertEquals(loginPage.getPasswordErrorMessage().getText(), DataProperties.dataProperty("data.properties","login.wrong.password"));
    }

    @Test (priority = 2)
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

    @Test (priority = 3)
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

    @Test (priority = 4)
    public void SendInvite(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet();
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        userHomePage.userMenuClick();
        userHomePage.accountSettingsClick();
        AccountSettingsPage settingsPage = PageFactory.initElements(driver, AccountSettingsPage.class);
        settingsPage.sendInvite();
        dbClearUser.getClean();
        settingsPage.goExit();
    }

    @Test(priority = 7)
    public void ErrorPageCheck(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet();
        driver.get(baseUrl+"/sfosfosifjsod");
        ErrorPage errorPage = PageFactory.initElements(driver, ErrorPage.class);
        assertEquals(errorPage.checkGoMainPageLinkResponseCode(),200);
        assertEquals(errorPage.getTitleText(), "404");
    }
}
