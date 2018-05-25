package BrowsersTests;

import Pages.LoginPage;
import Pages.UserHomePage;
import core.WebDriverTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static utils.PropertiesCache.getProperty;

public class PitiUiTest extends WebDriverTestBase {

    private final String baseUrl = "http://ang.chis.kiev.ua";

    @Test
    public void OpenSingUp() {
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        assertTrue(loginPage.isLogoExists());
        //assertEquals(dataProperty("login.page.title"),loginPage.getTitleText());
        assertTrue(loginPage.getEmail().isDisplayed());
        assertTrue(loginPage.getPass().isDisplayed());
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

}
