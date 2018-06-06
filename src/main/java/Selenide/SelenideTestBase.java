package Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class SelenideTestBase {
    private String browser = System.getProperty("browser",CHROME);
    public RemoteWebDriver driver;

    @BeforeMethod
    private void SetUp() throws MalformedURLException {
        int i=0;
        if (i==1){
        Configuration.browser = browser;
        switch (browser) {
            case CHROME:
                ChromeDriverManager.getInstance().setup();
                break;
        }}else {


            DesiredCapabilities selenide = new DesiredCapabilities();
            selenide.setBrowserName("chrome");
            selenide.setVersion("67");
            selenide.setCapability("enableVNC", true);
            String urlToRemoteWD = "http://18.195.216.182:4444/wd/hub";
            RemoteWebDriver driver =new RemoteWebDriver(new URL(urlToRemoteWD),selenide);
            WebDriverRunner.setWebDriver(driver);
            //driver = new RemoteWebDriver(URI.create("http://18.195.216.182:4444/wd/hub").toURL(), selenide);
            driver.manage().window().setSize(new Dimension(1920, 1080));
        }

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
