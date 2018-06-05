package Mail;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.FIREFOX;
import static org.openqa.selenium.remote.BrowserType.CHROME;

public class SelenideMail {
    private String browser = System.getProperty("browser",CHROME);

    @BeforeMethod
    private void SetUp() {
        Configuration.browser = browser;
        switch (browser) {
            case CHROME:
                ChromeDriverManager.getInstance().setup();
                break;
            case FIREFOX:
                FirefoxDriverManager.getInstance().setup();
                break;
        }

    }

    @Test
    public void mailTest() {
        open("https://gmail.com");

    }
}
