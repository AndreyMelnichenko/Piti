package Mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

import static utils.PropertiesCache.getProperty;

public class PasswordPage extends Util {
    public PasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private WebElement passwordField;
    @FindBy(how = How.XPATH, using = "(//span[@class='RveJvd snByac'])[1]")
    private WebElement nextButton;

    public WebElement getPasswordField(){
        WebElement element = waitFor(ExpectedConditions.visibilityOf(passwordField));
        return element;
    }
    public WebElement getNextButton(){
        return nextButton;
    }
    public void goPassword(){
        getPasswordField().sendKeys(getProperty("password.gmail"));
        getNextButton().click();
    }
}
