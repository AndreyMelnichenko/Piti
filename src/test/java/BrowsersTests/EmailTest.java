package BrowsersTests;

import Mail.MailLoginPage;
import Mail.MailMainPage;
import Mail.PasswordPage;
import core.WebDriverTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class EmailTest extends WebDriverTestBase {

    @Test
    public void EmailInviteChecker(){
        driver.get(gmail);
        MailLoginPage mailLoginPage = PageFactory.initElements(driver, MailLoginPage.class);
        mailLoginPage.handleSingIn(driver,gmail);
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
