package Pages;

import com.codeborne.selenide.Condition;
import org.testng.AssertJUnit;

import static utils.DataProperties.dataProperty;
import static utils.PropertiesCache.getProperty;

public class PagesActions {
    private Login login = new Login();
    private HomePage homePage = new HomePage();

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
        AssertJUnit.assertEquals(login.title().getText(),dataProperty("data.properties", "login.page.title"));
    }
}
