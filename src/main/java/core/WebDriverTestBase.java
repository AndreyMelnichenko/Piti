package core;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
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
    protected final String baseUrl = "http://185.156.41.135/login";
    protected final String gmail = "https://www.google.com/intl/ru/gmail/about/";
    private String runType = "prod";

    @BeforeClass
    public void setUp() throws Exception {
        switch (runType) {
            case ("debug"):
                //if (System.getProperty("user.name").equals("andrey"))
                ChromeDriverManager.getInstance().setup();
                Point point = new Point(1920, 0);
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                driver.manage().window().setPosition(point);
                driver.manage().window().maximize();
                break;
            case ("prod"):
                DesiredCapabilities browser = new DesiredCapabilities();
                browser.setBrowserName("chrome");
                browser.setVersion("64");
                browser.setCapability("enableVNC", true);
                //browser.setCapability("enableVideo", true);
                //driver = new RemoteWebDriver(URI.create("http://18.195.216.182:4444/wd/hub").toURL(), browser);
                driver = new RemoteWebDriver(URI.create("http://18.197.43.132:4444/wd/hub").toURL(), browser);
                driver.manage().window().setSize(new Dimension(1920, 1080));
                driver.manage().deleteAllCookies();
                dbClearUser.getClean();
                break;
        }
    }

    @BeforeMethod
    public void clearData(){
        driver.manage().deleteAllCookies();
        dbClearUser.getClean();
    }


    @AfterClass
    public void tearDown() {
        switch (runType) {
            case ("debug"):
                break;
            case ("prod"):
                if (driver != null) {
                    driver.quit();
                    driver = null;
                }
                break;
        }
    }
}
