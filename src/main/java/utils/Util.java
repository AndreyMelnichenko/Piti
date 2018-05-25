package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Util {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public Util(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(this.webDriver, 5);
    }
    public WebElement waitFor(ExpectedCondition<WebElement> expectedCondition){
        return webDriverWait.until(expectedCondition);
    }
}
