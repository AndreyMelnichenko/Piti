package Mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

import static utils.PropertiesCache.getProperty;

public class MailLoginPage extends Util {
    public MailLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//input[@type='email']")
    private WebElement emailInput;
    @FindBy(how = How.XPATH, using = "(//span[@class='RveJvd snByac'])[3]")
    private WebElement nextButton;
    @FindBy(how = How.XPATH, using = "//a[@data-g-label='Sign in']")
    private WebElement singIn;

    private WebElement getSingIn(){
        return waitFor(ExpectedConditions.visibilityOf(singIn));
    }

    private WebElement getEmailInput(){
        return waitFor(ExpectedConditions.visibilityOf(emailInput));
    }
    private WebElement getNextButton(){
        return waitFor(ExpectedConditions.visibilityOf(nextButton));
    }

    public void goEmail(){
        getEmailInput().sendKeys(getProperty("user.gmail"));
        getNextButton().click();
    }

    public void handleSingIn(WebDriver driver, String gmail){
        if (!(driver.getCurrentUrl().equals(gmail))){
            getSingIn().click();
        }
    }
}
