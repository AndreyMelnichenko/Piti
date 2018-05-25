package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

public class LoginPage extends Util {
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//img")
    private WebElement logo;
    @FindBy(how = How.XPATH, using = "//div[@class='auth_title']")
    private WebElement titleText;
    @FindBy (how = How.XPATH, using = "//input[@name='email']")
    private WebElement email;
    @FindBy (how = How.XPATH, using = "//input[@name='password']")
    private WebElement pass;
    @FindBy (how=How.XPATH, using = "//button[@class='login']")
    private WebElement inputButton;
    @FindBy (how = How.XPATH, using = "//button[@class='demo']")
    private WebElement demoButton;

    public boolean isLogoExists(){
        WebElement pitLogo = waitFor(ExpectedConditions.visibilityOf(logo));
        return pitLogo.isDisplayed();
    }

    public String getTitleText(){
        return titleText.getText();
    }

    public WebElement getEmail(){
        return email;
    }

    public WebElement getPass (){
        return pass;
    }

    public WebElement getInputButton(){
        return inputButton;
    }

    public WebElement getDemoButton(){
        return demoButton;
    }



}
