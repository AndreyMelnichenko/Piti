package Mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

public class MailLoginPage extends Util {
    public MailLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//input[@type='email']")
    private WebElement emailInput;
    @FindBy(how = How.XPATH, using = "(//span[@class='RveJvd snByac'])[3]")
    private WebElement nextButton;

    public WebElement getEmailInput(){
        WebElement element = waitFor(ExpectedConditions.visibilityOf(emailInput));
        return element;
    }
    public WebElement getNextButton(){
        return nextButton;
    }
}
