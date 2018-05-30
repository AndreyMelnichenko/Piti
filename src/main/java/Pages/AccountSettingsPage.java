package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

import static utils.PropertiesCache.getProperty;

public class AccountSettingsPage extends Util {
    public AccountSettingsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "(//div[@class='bottom'])/div[1]")
    private WebElement sendInvite;
    @FindBy(how = How.XPATH, using = "(//input[@name='email'])[1]")
    private WebElement invitedEmail;
    @FindBy(how = How.XPATH, using = "//textarea")
    private WebElement textMessage;
    @FindBy(how = How.XPATH, using = "(//span[@class='checkmark'])[3]")
    private WebElement simpleUserRole;
    @FindBy(how = How.XPATH, using = "(//button[@class='inviteUser_accept'])[2]")
    private WebElement acceptSendInvite;


    public WebElement sendInviteButton(){
        WebElement element = waitFor(ExpectedConditions.visibilityOf(sendInvite));
        return element;
    }

    public WebElement getInvitedEmail(){
        WebElement element = waitFor(ExpectedConditions.visibilityOf(invitedEmail));
        return element;
    }

    public WebElement getTextMessage(){
        WebElement element = waitFor(ExpectedConditions.visibilityOf(textMessage));
        return element;
    }

    public WebElement getSimpleUserRole(){
        WebElement element = waitFor(ExpectedConditions.visibilityOf(simpleUserRole));
        return element;
    }

    public WebElement getAcceptSendInvite(){
        WebElement element = waitFor(ExpectedConditions.visibilityOf(acceptSendInvite));
        return element;
    }

    public void sendInvite(){
        sendInviteButton().click();
        getInvitedEmail().click();
        getInvitedEmail().sendKeys(getProperty("user.gmail"));
        getTextMessage().click();
        getTextMessage().sendKeys("Welcome to PIT Service");
        getSimpleUserRole().click();
        getAcceptSendInvite().click();
    }
}
