package core;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class WebDriverTestBase {
    public RemoteWebDriver driver;
    protected final String baseUrl = "http://ang.chis.kiev.ua";
    protected final String gmail = "https://mail.google.com";

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
            browser.setVersion("67");
            browser.setCapability("enableVNC", true);
            browser.setCapability("enableVideo", true);
            driver = new RemoteWebDriver(URI.create("http://http://18.237.253.181:4444/wd/hub").toURL(), browser);
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
