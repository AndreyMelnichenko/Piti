package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

import static utils.PropertiesCache.getProperty;

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
    @FindBy (how = How.XPATH, using = "(//span[@class='err'])[1]")
    private WebElement loginErrorMessage;
    @FindBy (how = How.XPATH, using = "(//span[@class='err'])[2]")
    private WebElement passwordErrorMessage;
    @FindBy(how = How.XPATH, using = "//a[@href='/register']")
    private WebElement registrationLink;
    @FindBy(how = How.XPATH, using = "//a[@class='forget']")
    private WebElement forgetLink;

    public boolean isLogoExists(){
        WebElement pitLogo = waitFor(ExpectedConditions.visibilityOf(logo));
        return pitLogo.isDisplayed();
    }

    public String getTitleText(){
        return titleText.getText();
    }

    public WebElement getEmail(){
        return waitFor(ExpectedConditions.visibilityOf(email));
    }

    public WebElement getPass (){
        return waitFor(ExpectedConditions.visibilityOf(pass));
    }

    private WebElement getInputButton(){
        return waitFor(ExpectedConditions.elementToBeClickable(inputButton));
    }

    public WebElement getLoginErrorMessage() { return waitFor(ExpectedConditions.visibilityOf(loginErrorMessage));}

    public WebElement getPasswordErrorMessage(){
        return waitFor(ExpectedConditions.visibilityOf(passwordErrorMessage));
    }

    public WebElement getRegistrationLink(){
        return waitFor(ExpectedConditions.visibilityOf(registrationLink));
    }

    private WebElement getForgetLink(){
        return waitFor(ExpectedConditions.visibilityOf(forgetLink));
    }

    public void goPersonalCabinetWithBadAccess(){
        getEmail().sendKeys("user@user.com");
        getPass().sendKeys("password");
        getInputButton().click();
    }

    public void goPersonalCabinet(String user, String pass){
        getEmail().sendKeys(getProperty(user));
        getPass().sendKeys(getProperty(pass));
        getInputButton().click();
    }

    public void goForgetPage(){
        getForgetLink().click();
    }

}
