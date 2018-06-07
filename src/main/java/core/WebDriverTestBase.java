package core;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.dbClearUser;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class WebDriverTestBase {
    public RemoteWebDriver driver;
    //protected final String baseUrl = "http://ang.chis.kiev.ua";
    protected final String baseUrl = "http://185.156.41.135/login";
    protected final String gmail = "https://www.google.com/intl/ru/gmail/about/";

    @BeforeClass
    public void setUp() throws Exception{
        if (System.getProperty("user.name").equals("andrey")) {
            ChromeDriverManager.getInstance().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } else {
            DesiredCapabilities browser = new DesiredCapabilities();
            browser.setBrowserName("chrome");
            browser.setVersion("66");
            browser.setCapability("enableVNC", true);
            //browser.setCapability("enableVideo", true);
            driver = new RemoteWebDriver(URI.create("http://18.195.216.182:4444/wd/hub").toURL(), browser);
            //driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), browser);
            driver.manage().window().setSize(new Dimension(1920, 1080));
        }
    }
    @BeforeMethod
    public void clearDb(){
        dbClearUser.getClean();
        driver.manage().deleteAllCookies();
    }

/*    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }*/
}
