package BrowsersTests;

import Mail.MailLoginPage;
import Mail.MailMainPage;
import Mail.PasswordPage;
import Pages.LoginPage;
import Pages.RegistrationPage;
import Pages.UserHomePage;
import core.WebDriverTestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utils.DataProperties;
import utils.dbClearUser;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static utils.PropertiesCache.getProperty;

public class PitiUiTest extends WebDriverTestBase {

    private final String baseUrl = "http://ang.chis.kiev.ua";
    private final String gmail = "https://gmail.com";

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
    public void SingUpErr(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.getEmail().sendKeys("user@user.com");
        loginPage.getPass().sendKeys("password");
        loginPage.getInputButton().click();
        assertEquals(loginPage.getLoginErrorMessage().getText(), DataProperties.dataProperty("data.properties","login.wrong.email"));
        assertEquals(loginPage.getPasswordErrorMessage().getText(), DataProperties.dataProperty("data.properties","login.wrong.password"));
    }

    @Test
    public void SingUp(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.getEmail().sendKeys(getProperty("user.email"));
        loginPage.getPass().sendKeys(getProperty("user.password"));
        loginPage.getInputButton().click();
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        assertTrue(userHomePage.isMap());
    }

    @Test
    public void Registration(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.getRegistrationLink().click();
        RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        registrationPage.getEmailField().sendKeys(getProperty("new.user.email"));
        registrationPage.getPasswordField().sendKeys(getProperty("new.user.password"));
        registrationPage.getPasswordConfirm().sendKeys(getProperty("new.user.password"));
        registrationPage.getButtonCreate().click();
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        assertTrue(userHomePage.isMap());
        dbClearUser.getClean();
    }

    @Test
    public void EmailCheck(){
        driver.get(gmail);
        MailLoginPage mailLoginPage = PageFactory.initElements(driver, MailLoginPage.class);
        mailLoginPage.getEmailInput().sendKeys(getProperty("user.gmail"));
        mailLoginPage.getNextButton().click();
        PasswordPage passwordPage = PageFactory.initElements(driver, PasswordPage.class);
        passwordPage.getPasswordField().sendKeys(getProperty("password.gmail"));
        passwordPage.getNextButton().click();
        MailMainPage mailMainPage = PageFactory.initElements(driver, MailMainPage.class);
        JavascriptExecutor executor = driver;
        executor.executeScript("arguments[0].click();", mailMainPage.getChooseAll());
        JavascriptExecutor executor2 = driver;
        executor2.executeScript("arguments[0].click();", mailMainPage.getDeleteAll());
    }
}
