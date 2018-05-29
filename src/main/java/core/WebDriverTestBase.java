package core;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class WebDriverTestBase {
    protected RemoteWebDriver driver;

    @BeforeClass
    public void setUp(@Optional String browserName, @Optional String browserVersion) throws Exception{
        if (System.getProperty("user.name").equals("andrey")) {
            ChromeDriverManager.getInstance().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } else {
            DesiredCapabilities browser = new DesiredCapabilities();
            browser.setBrowserName("chrome");
            browser.setVersion("64");
            browser.setCapability("enableVNC", true);
            driver = new RemoteWebDriver(URI.create("http://18.197.43.132:4444/wd/hub").toURL(), browser);
            driver.manage().window().setSize(new Dimension(1920, 1080));
        }
    }

    @AfterClass
    public void tearDown(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
