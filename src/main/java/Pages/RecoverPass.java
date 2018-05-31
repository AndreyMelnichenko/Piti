package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DataProperties;
import utils.Util;

import static utils.PropertiesCache.getProperty;

public class RecoverPass extends Util {
    public RecoverPass(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(how = How.XPATH, using = "//div[@class='auth_title']")
    private WebElement formTitle;
    @FindBy (how = How.XPATH, using = "//input[@formcontrolname='email']")
    private WebElement inputEmail;
    @FindBy(how = How.XPATH, using = "//button[@class='recoverBtn']")
    private WebElement recoverButton;

    private WebElement getFormTitle(){
        return waitFor(ExpectedConditions.visibilityOf(formTitle));
    }
    private WebElement getInputEmail(){
        return waitFor(ExpectedConditions.visibilityOf(inputEmail));
    }

    private WebElement getRecoverButton(){
        return waitFor(ExpectedConditions.visibilityOf(recoverButton));
    }

    public boolean isTextTitle(){
        return  getFormTitle().getText().equals(DataProperties.dataProperty("data.properties","recovery.page.title"));
    }

    public void inputEmailToRecover(){
        getFormTitle().click();
        getInputEmail().sendKeys(getProperty("user.email"));
    }

    public void clickButton(){
        getRecoverButton().click();
    }
}
