package BrowsersTests;

import Pages.ErrorPage;
import Pages.LoginPage;
import Pages.RecoverPass;
import Pages.RecoverSuccess;
import core.WebDriverTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utils.DataProperties;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class ErrorMessageTest extends WebDriverTestBase {

    @Test
    public void waitForWatcher(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SingUpErr(){
        driver.get(baseUrl);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinetWithBadAccess();
        assertEquals(loginPage.getLoginErrorMessage().getText(), DataProperties.dataProperty("data.properties","login.wrong.email"));
        assertEquals(loginPage.getPasswordErrorMessage().getText(), DataProperties.dataProperty("data.properties","login.wrong.password"));
    }

    @Test
    public void ErrorPageCheck(){
        driver.get(baseUrl+"/sfosfosifjsod");
        ErrorPage errorPage = PageFactory.initElements(driver, ErrorPage.class);
        assertEquals(errorPage.checkGoMainPageLinkResponseCode(),200);
        assertEquals(errorPage.getTitleText(), "404");
    }

    @Test
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
}
