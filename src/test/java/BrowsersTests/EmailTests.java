package BrowsersTests;

import Mail.MailLoginPage;
import Mail.MailMainPage;
import Mail.PasswordPage;
import core.WebDriverTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class EmailTests extends WebDriverTestBase {
    @Test(priority = 5)
    public void EmailInviteChecker(){
        driver.get(gmail);
        MailLoginPage mailLoginPage = PageFactory.initElements(driver, MailLoginPage.class);
        mailLoginPage.goEmail();
        PasswordPage passwordPage = PageFactory.initElements(driver, PasswordPage.class);
        passwordPage.goPassword();
        MailMainPage mailMainPage = PageFactory.initElements(driver, MailMainPage.class);
        assertTrue(mailMainPage.getEmailTitle());
        mailMainPage.cleanEmailList();
    }

    @Ignore
    @Test(priority = 6)
    public void EmailCleaner(){
        driver.get(gmail);
        MailLoginPage mailLoginPage = PageFactory.initElements(driver, MailLoginPage.class);
        mailLoginPage.goEmail();
        PasswordPage passwordPage = PageFactory.initElements(driver, PasswordPage.class);
        passwordPage.goPassword();
        MailMainPage mailMainPage = PageFactory.initElements(driver, MailMainPage.class);
        mailMainPage.cleanEmailList();
    }
}
