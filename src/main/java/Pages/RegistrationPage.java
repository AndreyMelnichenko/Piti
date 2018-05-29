package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.Util;

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

    public WebElement getEmailField(){
        return email;
    }

    public WebElement getPasswordField (){
        return password;
    }

    public WebElement getPasswordConfirm(){
        return passwordConfirm;
    }

    public WebElement getButtonCreate(){
        return buttonCreate;
    }
}
