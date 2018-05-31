package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    @FindBy(how = How.XPATH, using = "//div[@class='menuBtn_box']")
    private WebElement menuButton;
    @FindBy(how = How.XPATH, using = "//div[@class='menu_item']")
    private WebElement exitButton;


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
        WebElement element = waitFor(ExpectedConditions.elementToBeClickable(acceptSendInvite));
        return element;
    }

    public WebElement getMenuButton(){
        WebElement element = waitFor(ExpectedConditions.elementToBeClickable(menuButton));
        return element;
    }

    public WebElement getExitButton(){
        WebElement element = waitFor(ExpectedConditions.elementToBeClickable(exitButton));
        return element;
    }

    public void sendInvite(WebDriver driver){
        sendInviteButton().click();
        getInvitedEmail().click();
        getInvitedEmail().sendKeys(getProperty("user.gmail"));
        getTextMessage().click();
        getTextMessage().sendKeys("Welcome to PIT Service");
        getSimpleUserRole().click();
        System.out.println(getAcceptSendInvite().getText());
        Actions action = new Actions(driver);
        action.moveToElement(getAcceptSendInvite()).perform();
        action.moveToElement(getAcceptSendInvite()).click().perform();
        //getAcceptSendInvite().click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void goExit(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getMenuButton().click();
        getExitButton().click();
    }
}
