package BrowsersTests;

import Pages.AccountDevices;
import Pages.AccountSettingsPage;
import Pages.LoginPage;
import Pages.UserHomePage;
import core.WebDriverTestBase;
import io.qameta.allure.Description;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utils.CustomWait;

import static org.testng.Assert.assertTrue;

public class DevelopTest extends WebDriverTestBase {
/*    private static String user1="user.email";
    private static String pass1="user.password";
    private static String user2="user2.email";
    private static String pass2="user2.password";

    @Test(priority = 11)
    @Description("Add new device TK-116 to user")
    public void AddDevice(){
        driver.get(baseUrl);
        CustomWait.getHalfSecondWait();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet(user1,pass1);
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        userHomePage.userMenuClick();
        userHomePage.accountSettingsClick();
        AccountSettingsPage settingsPage = PageFactory.initElements(driver, AccountSettingsPage.class);
        settingsPage.goDevices();
        AccountDevices accountDevices = PageFactory.initElements(driver, AccountDevices.class);
        accountDevices.clickAddDevice();
        accountDevices.CreateDevice();
        assertTrue(accountDevices.isNewDeviceCreated());
        accountDevices.isNewDeviceRemoved(driver);
        CustomWait.getMinWait();
        accountDevices.goExitApp(driver);

    }

    @Test(priority = 12)
    @Description("Add new device GT3101 to user")
    public void CheckDevice(){
        driver.get(baseUrl);
        CustomWait.getOneSecondWait();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goPersonalCabinet(user2,pass2);
        UserHomePage userHomePage = PageFactory.initElements(driver, UserHomePage.class);
        //userHomePage.clickAddNewDevice();
        //userHomePage.fillFormGt3101();
        userHomePage.checkDeviceItem(driver);
        userHomePage.checkCarOnMap(driver);
        userHomePage.checkWiget(driver);
        userHomePage.setPeriodCalendar(driver);
        userHomePage.userMenuClick();
        userHomePage.exitHomePage();
    }*/
}
