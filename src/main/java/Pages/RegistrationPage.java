package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

import static utils.PropertiesCache.getProperty;

public class RegistrationPage extends Util {

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    private WebElement email;
    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    private WebElement password;
    @FindBy(how = How.XPATH, using = "//input[@name='passwordConfirm']")
    private WebElement passwordConfirm;
    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement buttonCreate;

    private WebElement getEmailField(){
        return waitFor(ExpectedConditions.visibilityOf(email));
    }

    private WebElement getPasswordField (){
        return waitFor(ExpectedConditions.visibilityOf(password));
    }

    private WebElement getPasswordConfirm(){
        return waitFor(ExpectedConditions.visibilityOf(passwordConfirm));
    }

    private WebElement getButtonCreate(){
        return waitFor(ExpectedConditions.visibilityOf(buttonCreate));
    }

    public void goRegistration(){
        getEmailField().sendKeys(getProperty("new.user.email"));
        getPasswordField().sendKeys(getProperty("new.user.password"));
        getPasswordConfirm().sendKeys(getProperty("new.user.password"));
        getButtonCreate().click();
    }
}
