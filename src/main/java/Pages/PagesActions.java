package Pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.ui.Select;
import utils.CustomWait;
import utils.RandomMinMax;

import static org.testng.Assert.assertEquals;
import static utils.DataProperties.dataProperty;
import static utils.PropertiesCache.getProperty;

public class PagesActions {
    private Login login = new Login();
    private HomePage homePage = new HomePage();
    private Recovery recovery = new Recovery();
    private Registration registration = new Registration();

    public void enterToPersonalCabinet(){
        login.login().setValue(getProperty("user.email"));
        login.password().setValue(getProperty("user.password"));
        login.enter().click();
        homePage.map().shouldBe(Condition.visible);
    }

    public void exitFromPersonalCabinet(){
        homePage.menu().shouldBe(Condition.visible).click();
        homePage.exit().shouldBe(Condition.visible).click();
        login.logo().waitUntil(Condition.visible, 6000);
    }

    public void checkLogoPage(){
        login.logo().shouldBe(Condition.visible);
        assertEquals(login.title().getText(),dataProperty("data.properties", "login.page.title"));
    }

    public void checkAuthorizationPage(){
        login.logo().shouldBe(Condition.visible);
        assertEquals(login.authorisation().shouldBe(Condition.visible).getText(),dataProperty("data.properties", "auth.title"));
    }

    public void checkRecoverPage(){
        login.recoverPass().shouldBe(Condition.visible).click();
        recovery.title().should(Condition.matchesText(dataProperty("data.properties","recovery.page.title")));
    }

    public void checkresetPage(){
        assertEquals(recovery.resetTitle().getText(),dataProperty("data.properties","recovery.page.title"));
    }

    public void checkRegistrationPage(){
        login.registration().shouldBe(Condition.visible).click();
        registration.emailField().shouldBe(Condition.visible).setValue(getProperty("new.user.email"));
        registration.passwordField().shouldBe(Condition.visible).setValue(getProperty("new.user.password"));
        registration.passwordConfirmField().shouldBe(Condition.visible).setValue(getProperty("new.user.password"));
        Select selectDevice = new Select(registration.listTimeZone());
        selectDevice.selectByIndex(RandomMinMax.Go(1,20));
        CustomWait.getOneSecondWait();
        registration.buttonCreate().shouldBe(Condition.visible).click();
        CustomWait.getTwoSecondWait();
    }
}
