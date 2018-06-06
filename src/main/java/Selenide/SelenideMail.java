package Selenide;

import core.WebDriverTestBase;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static utils.PropertiesCache.getProperty;

public class SelenideMail extends WebDriverTestBase {

    @Test
    public void mailTest() {
        open("https://www.google.com/intl/ru/gmail/about/");
        SelenideMailLogin selenideMailLogin = new SelenideMailLogin();
        selenideMailLogin.singIn().click();
        selenideMailLogin.emailSelenide().setValue(getProperty("user.gmail"));
        selenideMailLogin.nextButton().click();
    }

    @Test
    public void addDevice(){

    }
}
