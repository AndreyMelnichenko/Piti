package core;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.TestListener;
import utils.dbClearUser;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@Listeners({TestListener.class})
public class WebDriverTestBase {
    protected final String baseUrl = "http://185.156.41.135/login";
    public RemoteWebDriver driver;
    private String runType = "local";

    @BeforeClass
    public void setup() throws MalformedURLException {
        switch (runType){
            case("local"):
                Configuration.browser = "chrome";
                Configuration.browserPosition="1921x0";
                Configuration.browserSize="1800x1000";
                Configuration.reportsFolder = "src/main/java/screen";
                Configuration.savePageSource=false;
                break;
            case("docker"):
                Configuration.browser = "chrome";
                DesiredCapabilities browser = new DesiredCapabilities();
                browser.setBrowserName("chrome");
                browser.setVersion("66");
                browser.setCapability("enableVNC", true);
                driver = new RemoteWebDriver(URI.create("http://18.195.216.182:4444/wd/hub").toURL(),browser);
                setWebDriver(driver);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
}
