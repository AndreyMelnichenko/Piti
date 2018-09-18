package core;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import utils.TestListener;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@Listeners({TestListener.class})
public class WebDriverTestBase {
    protected final String baseUrl = "https://n2.chis.kiev.ua";
    public RemoteWebDriver driver;
    protected String runType = "docker";

    @Parameters({"browser"})
    @BeforeClass
    public void setup(@Optional String browser) throws MalformedURLException {
        switch (runType){
            case("local"):
                Configuration.browser = browser;
                if (browser==null){
                    Configuration.browser ="chrome";
                }
                Configuration.browserPosition="1980x0";
                Configuration.browserSize="1800x1000";
                Configuration.reportsFolder = "src/main/java/screen";
                Configuration.savePageSource=false;
                break;
            case("docker"):
                Configuration.browser = browser;
                Configuration.reportsFolder = "src/main/java/screen";
                Configuration.savePageSource=false;
                DesiredCapabilities browserCaps = new DesiredCapabilities();
                browserCaps.setBrowserName("chrome");
                browserCaps.setVersion("66");
                browserCaps.setCapability("enableVNC", true);
                driver = new RemoteWebDriver(URI.create("http://18.195.216.182:4444/wd/hub").toURL(),browserCaps);
                //browserCaps.setCapability("enableVideo", true);
                //driver = new RemoteWebDriver(URI.create("http://selenoid.lenal.com.ua:4444/wd/hub").toURL(),browserCaps);
                setWebDriver(driver);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().setSize(new Dimension(1920, 1080));
                break;
        }

    }

//    @BeforeMethod
//    public void dbCleaner(){
//        dbConnect.getClean();
//    }

    @AfterClass
    public void tearDown(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}