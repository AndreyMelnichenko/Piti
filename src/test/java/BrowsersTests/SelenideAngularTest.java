package BrowsersTests;

import Pages.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CustomWait;
import utils.DataProperties;
import utils.dbClearUser;

import java.net.MalformedURLException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static utils.PropertiesCache.getProperty;

public class SelenideAngularTest {

    private final String baseUrl = "http://185.156.41.135/login";
    private RemoteWebDriver driver;
    private SelenideLogin login = new SelenideLogin();
    private SelenideHomePage homePage = new SelenideHomePage();
    private SelenideErrPage errPage = new SelenideErrPage();
    private SelenideRecovery recovery = new SelenideRecovery();
    private SelenideRegistration registration = new SelenideRegistration();
    private SelenideAccountSettings accountSettings = new SelenideAccountSettings();
    private String runType = "docker";

    @BeforeClass
    public void setup() throws MalformedURLException {
        switch (runType){
            case("local"):
                Configuration.browser = "chrome";
                Configuration.browserPosition="1921x0";
                Configuration.browserSize="1800x1000";
                break;
            case("docker"):
                Configuration.browser = "chrome";
                DesiredCapabilities browser = new DesiredCapabilities();
                browser.setBrowserName("chrome");
                browser.setVersion("66");
                browser.setCapability("enableVNC", true);
                driver = new RemoteWebDriver(URI.create("http://18.195.216.182:4444/wd/hub").toURL(),browser);
                setWebDriver(driver);
                driver.manage().window().setSize(new Dimension(1920, 1080));
                break;
        }

    }

    @BeforeMethod
    public void dbCleaner(){
        dbClearUser.getClean();
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
        AssertJUnit.assertEquals(login.title().getText(),DataProperties.dataProperty("data.properties", "login.page.title"));

    }
    @Test(dependsOnMethods = "logo")
    public void SingInErrMessages(){
        login.login().setValue("qatest@email.my");
        login.password().setValue("123qwwedsa");
        login.enter().click();
        AssertJUnit.assertEquals(login.errorLogin().getText(), DataProperties.dataProperty("data.properties", "login.wrong.email"));
        AssertJUnit.assertEquals(login.errorPassword().getText(), DataProperties.dataProperty("data.properties", "login.wrong.password"));
    }

    @Test(dependsOnMethods = "SingInErrMessages")
    public void recoveryPassword(){
        login.recoverPass().shouldBe(Condition.visible).click();
        recovery.title().should(Condition.matchesText(DataProperties.dataProperty("data.properties","recovery.page.title")));
        recovery.emailField().shouldBe(Condition.visible).setValue(getProperty("user.email"));
        recovery.recoveryButton().shouldBe(Condition.visible).click();

    }
    @Test(dependsOnMethods = "recoveryPassword")
    public void badRegistration(){
        open(baseUrl);
        login.registration().shouldBe(Condition.visible).click();
        registration.emailField().shouldBe(Condition.visible).setValue(getProperty("user.email"));
        registration.passwordField().shouldBe(Condition.visible).setValue(getProperty("user.password"));
        registration.passwordConfirmField().shouldBe(Condition.visible).setValue(getProperty("user.password"));
        registration.buttonCreate().shouldBe(Condition.visible).click();
        registration.errorMessage().shouldBe(Condition.visible).should(Condition.matchesText("Электронная почта \"dima.laktionov5@gmail.com\" has already been taken."));
    }

    @Test(enabled=false,dependsOnMethods = "badRegistration")
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
        CustomWait.getTwoSecondWait();
        login.logo().waitUntil(Condition.visible, 6000);//shouldBe(Condition.visible);
    }

    @Test (dependsOnMethods = "recoveryPassword")
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
        AssertJUnit.assertEquals(errPage.errTitle().getText(), "404");
        $(By.xpath("//a")).click();
        homePage.map().shouldBe(Condition.visible);
    }

    @Test(dependsOnMethods = "errorPage")
    public void addUser(){
        homePage.menu().shouldBe(Condition.visible).click();
        homePage.accountSettings().shouldBe(Condition.visible).click();
        accountSettings.createNewUserButton().shouldBe(Condition.visible).click();
        accountSettings.emailNewUser().shouldBe(Condition.visible).setValue(getProperty("new.user.email"));
        accountSettings.nameNewUser().shouldBe(Condition.visible).setValue(getProperty("new.user.fio"));
        accountSettings.passNewUser().shouldBe(Condition.visible).setValue(getProperty("new.user.password"));
        accountSettings.passNewUserConfirm().shouldBe(Condition.visible).setValue(getProperty("new.user.password"));
        accountSettings.phoneNewUser().shouldBe(Condition.visible).setValue(getProperty("new.user.phone"));
        accountSettings.roleNewUser().shouldBe(Condition.visible).click();
        accountSettings.acceptCreateNewUser().shouldBe(Condition.visible).click();
        CustomWait.getTwoSecondWait();
        CustomWait.getOneSecondWait();
        Selenide.refresh();
        accountSettings.mainArea().waitUntil(Condition.visible,5000);
    }

    @Test(dependsOnMethods = "addUser")
    public void checkCreatedUser(){
        accountSettings.createdUserEmail().should(Condition.matchesText(getProperty("new.user.email")));
        accountSettings.createdUserName().should(Condition.matchesText(getProperty("new.user.fio")));
        accountSettings.createdUserPhone().should(Condition.matchesText(getProperty("new.user.phone")));
    }


    @Test(dependsOnMethods = "checkCreatedUser")
    public void invitetoUser(){
        accountSettings.inviteButton().shouldBe(Condition.visible).click();
        accountSettings.emailForImvite().shouldBe(Condition.visible).setValue(getProperty("user.gmail"));
        accountSettings.messageForInvite().shouldBe(Condition.visible).setValue("Welcome to PIT Service");
        accountSettings.simpleRoleInvite().shouldBe(Condition.visible).click();
        accountSettings.acceptSendInvite().shouldBe(Condition.visible).click();
        CustomWait.getOneSecondWait();
        Selenide.refresh();
        accountSettings.mainArea().waitUntil(Condition.visible,2000);
    }

    @Test(dependsOnMethods = "invitetoUser")
    public void userChangeInfo(){
        String oldName = accountSettings.firstUserName().getText();
        accountSettings.firstUserThreeDots().shouldBe(Condition.visible).click();
        accountSettings.firstUserEdit().shouldBe(Condition.visible).click();
        accountSettings.fitstUserOldEmail().should(Condition.visible).setValue(getProperty("user.email"));
        String newName = "Dima" + new SimpleDateFormat("_dd-MM-yyyy_HH:mm").format(Calendar.getInstance().getTime());
        accountSettings.firstUserOldName().should(Condition.visible).setValue(newName);
        accountSettings.firstUserOldPhone().should(Condition.visible).setValue(getProperty("new.user.phone"));
        accountSettings.firstUserTimeZone().should(Condition.visible).click();
        CustomWait.getMinWait();
        accountSettings.firstUserTimeZone().should(Condition.visible).click();
        accountSettings.firstUserAcceptNewInfo().should(Condition.visible).click();
        accountSettings.mainArea().waitUntil(Condition.visible, 2000);
        assertFalse(oldName.equals(accountSettings.firstUserName()));

    }

    @Test(dependsOnMethods = "userChangeInfo")
    public void addDevice(){
        accountSettings.devicesButton().should(Condition.visible).click();
        accountSettings.addDeviceButton().should(Condition.visible).click();
        accountSettings.newDeviceName().setValue(DataProperties.dataProperty("data.properties","TK116.name"));
        accountSettings.newDeviceImei().setValue(DataProperties.dataProperty("data.properties","TK116.imei"));
        Select selectDevice = new Select(accountSettings.newDeviceType());
        selectDevice.selectByVisibleText(DataProperties.dataProperty("data.properties","TK116.types"));
        accountSettings.newDevicePhone().setValue(DataProperties.dataProperty("data.properties","TK116.sim"));
        accountSettings.newDevicePass().setValue(DataProperties.dataProperty("data.properties","TK116.pass"));
        accountSettings.newDeviceShowPass().click();
        accountSettings.newDeviceApn().setValue(DataProperties.dataProperty("data.properties","TK116.apn"));
        accountSettings.newDeviceAccept().should(Condition.visible).click();
        CustomWait.getTwoSecondWait();
        CustomWait.getOneSecondWait();
        Selenide.refresh();
        accountSettings.newDeviceItem().waitUntil(Condition.visible,5000);
    }

    @Test(dependsOnMethods = "addDevice")
    public void removeDevice(){
        accountSettings.mainArea().waitUntil(Condition.visible, 2000);
        accountSettings.removeNewDevice().should(Condition.visible).click();
        accountSettings.removeNewDeviceButton().should(Condition.visible).click();
        accountSettings.removeNewDeviceConfirm().waitUntil(Condition.visible,2000).click();
        accountSettings.mainArea().waitUntil(Condition.visible, 2000);
        Selenide.refresh();
        accountSettings.contentField().should(Condition.matchesText(""));
    }

    @Test(dependsOnMethods = "removeDevice")
    public void exitPersonalCabinet(){
        accountSettings.menuButton().should(Condition.visible).click();
        accountSettings.exitButton().shouldBe(Condition.visible).click();
        login.logo().waitUntil(Condition.visible, 2000);
    }
}
