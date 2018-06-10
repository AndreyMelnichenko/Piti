package Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CustomWait;
import utils.DataProperties;

import java.net.MalformedURLException;
import java.net.URI;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.AssertJUnit.assertEquals;
import static utils.PropertiesCache.getProperty;

public class SelenideAngularTest {

    private final String baseUrl = "http://185.156.41.135/login";
    private RemoteWebDriver driver;
    private SelenideLogin login = new SelenideLogin();
    private SelenideHomePage homePage = new SelenideHomePage();
    private SelenideErrPage errPage = new SelenideErrPage();
    private SelenideRecovery recovery = new SelenideRecovery();
    private SelenideRegistration registration = new SelenideRegistration();
    private String runType = "local";

    @BeforeClass
    public void setup() throws MalformedURLException {
        switch (runType){
            case("local"):
                Configuration.browser = "chrome";
                break;
            case("docker"):
                Configuration.browser = "chrome";
                DesiredCapabilities browser = new DesiredCapabilities();
                browser.setBrowserName("chrome");
                browser.setVersion("66");
                browser.setCapability("enableVNC", true);
                driver = new RemoteWebDriver(URI.create("http://18.195.216.182:4444/wd/hub").toURL(),browser);
                WebDriverRunner.setWebDriver(driver);
                driver.manage().window().setSize(new Dimension(1920, 1080));
                driver.manage().deleteAllCookies();
                break;
        }

    }

    @BeforeMethod
    public void dbCleaner(){
        //dbClearUser.getClean();
    }
    @AfterClass
    public void tearDown(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    public void logo() {
        open(baseUrl);
        login.logo().shouldBe(Condition.visible);
        assertEquals(login.title().getText(),DataProperties.dataProperty("data.properties", "login.page.title"));

    }
    @Test(dependsOnMethods = "logo")
    public void errMessages(){
        login.login().setValue("user123@email.zae");
        login.password().setValue("123qwwedsa");
        login.enter().click();
        assertEquals(login.errorLogin().getText(), DataProperties.dataProperty("data.properties", "login.wrong.email"));
        assertEquals(login.errorPassword().getText(), DataProperties.dataProperty("data.properties", "login.wrong.password"));
    }

    @Test(dependsOnMethods = "errMessages")
    public void recoveryPassword(){
        login.recoverPass().shouldBe(Condition.visible).click();
        recovery.title().should(Condition.matchesText(DataProperties.dataProperty("data.properties","recovery.page.title")));
        recovery.emailField().shouldBe(Condition.visible).setValue(getProperty("user.email"));
        recovery.recoveryButton().shouldBe(Condition.visible).click();

    }

    @Test(dependsOnMethods = "recoveryPassword")
    public void registration(){
        open(baseUrl);
        login.registration().shouldBe(Condition.visible).click();
        registration.emailField().shouldBe(Condition.visible).setValue(getProperty("new.user.email"));
        registration.passwordField().shouldBe(Condition.visible).setValue(getProperty("new.user.password"));
        registration.passwordConfirmField().shouldBe(Condition.visible).setValue(getProperty("new.user.password"));
        registration.buttonCreate().shouldBe(Condition.visible).click();
        homePage.map().shouldBe(Condition.visible);
        homePage.menu().shouldBe(Condition.visible).click();
        homePage.exit().shouldBe(Condition.visible).click();
        CustomWait.getOneSecondWait();
        login.logo().shouldBe(Condition.visible);
    }

    @Test (dependsOnMethods = "registration")
    public void enterPersonalCabinet(){
        open(baseUrl);
        login.login().setValue(getProperty("user.email"));
        login.password().setValue(getProperty("user.password"));
        login.enter().click();
        homePage.map().shouldBe(Condition.visible);
    }



    @Test(dependsOnMethods = "enterPersonalCabinet")
    public void errorPage(){
        open(baseUrl+"/eeuheirf");
        assertEquals(errPage.errTitle().getText(), "404");
        $(By.xpath("//a")).click();
        homePage.map().shouldBe(Condition.visible);
    }

    @Test(dependsOnMethods = "errorPage")
    public void addUser(){
        homePage.menu().shouldBe(Condition.visible).click();
        homePage.accountSettings().shouldBe(Condition.visible).click();
    }

    @Test(enabled=false, dependsOnMethods = "addUser")
    public void exitPersonalCabinet(){
        homePage.exit().shouldBe(Condition.visible).click();
        login.logo().shouldBe(Condition.visible);
    }
}
