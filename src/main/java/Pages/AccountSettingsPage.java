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
    private WebElement inviteEmail;
    @FindBy(how = How.XPATH, using = "//textarea")
    private WebElement inviteTextMessage;
    @FindBy(how = How.XPATH, using = "(//span[@class='checkmark'])[3]")
    private WebElement inviteSimpleUserRole;
    @FindBy(how = How.XPATH, using = "(//button[@class='inviteUser_accept'])[2]")
    private WebElement acceptSendInvite;
    @FindBy(how = How.XPATH, using = "//div[@class='menuBtn_box']")
    private WebElement menuButton;
    @FindBy(how = How.XPATH, using = "//div[@class='menu_item']")
    private WebElement exitButton;
    //------------------
    @FindBy(how = How.XPATH, using = "(//div[@class='user_info-mail'])[2]")
    private WebElement newUserEmail; //second user position
    @FindBy(how = How.XPATH, using = "(//div[@class='user_info-name'])[2]")
    private WebElement newUserName;
    @FindBy(how = How.XPATH, using = "(//div[@class='user_info-phone'])[2]")
    private WebElement newUserPhone;
    //------------------
    @FindBy(how = How.XPATH, using = "(//input[@name='email'])[2]")
    private WebElement createNewUserEmail;
    @FindBy(how = How.XPATH, using = "(//div[@class='bottom'])/div[2]")
    private WebElement createNewUserButton;
    @FindBy(how = How.XPATH, using = "(//span[@class='checkmark'])[6]")
    private WebElement createNewUserSimpleRole;
    @FindBy(how = How.XPATH, using = "(//input[@name='name'])[1]")
    private WebElement createNewUserName;
    @FindBy(how = How.XPATH, using = "(//input[@name='password'])[1]")
    private WebElement createNewUserPass;
    @FindBy(how = How.XPATH, using = "(//input[@name='passwordConfirm'])[1]")
    private WebElement createNewUserConfirm;
    @FindBy(how = How.XPATH, using = "(//input[@name='phone'])[1]")
    private WebElement createNewUserPhone;
    @FindBy(how = How.XPATH, using = "(//button[@class='addUser_accept'])[2]")
    private WebElement createNewUserAccept;

    private WebElement getCreateNewUserButton(){
        return waitFor(ExpectedConditions.visibilityOf(createNewUserButton));
    }

    private WebElement getCreateNewUserAccept(){
        return waitFor(ExpectedConditions.visibilityOf(createNewUserAccept));
    }

    private WebElement getCreateNewUserPhone(){
        return waitFor(ExpectedConditions.visibilityOf(createNewUserPhone));
    }

    private WebElement getCreateNewUserConfirm(){
        return waitFor(ExpectedConditions.visibilityOf(createNewUserConfirm));
    }

    private WebElement getCreateNewUserPass(){
        return waitFor(ExpectedConditions.visibilityOf(createNewUserPass));
    }

    private WebElement getCreateNewUserName(){
        return waitFor(ExpectedConditions.visibilityOf(createNewUserName));
    }

    private WebElement getCreateNewUserSimpleRole(){
        return waitFor(ExpectedConditions.visibilityOf(createNewUserSimpleRole));
    }

    private WebElement getCreateNewUserEmail(){
        return waitFor(ExpectedConditions.visibilityOf(createNewUserEmail));
    }

    private WebElement sendInviteButton(){
        return waitFor(ExpectedConditions.visibilityOf(sendInvite));
    }

    private WebElement getInviteEmail(){
        return waitFor(ExpectedConditions.visibilityOf(inviteEmail));
    }

    private WebElement getInviteTextMessage(){
        return waitFor(ExpectedConditions.visibilityOf(inviteTextMessage));
    }

    private WebElement getInviteSimpleUserRole(){
        return waitFor(ExpectedConditions.visibilityOf(inviteSimpleUserRole));
    }

    private WebElement getAcceptSendInvite(){
        return waitFor(ExpectedConditions.elementToBeClickable(acceptSendInvite));
    }

    private WebElement getMenuButton(){
        return waitFor(ExpectedConditions.elementToBeClickable(menuButton));
    }

    private WebElement getExitButton(){
        return waitFor(ExpectedConditions.elementToBeClickable(exitButton));
    }

    private WebElement getNewUserEmail(){
        return waitFor(ExpectedConditions.visibilityOf(newUserEmail));
    }
    private WebElement getNewUserName(){
        return waitFor(ExpectedConditions.visibilityOf(newUserName));
    }
    private WebElement getNewUserPhone(){
        return waitFor(ExpectedConditions.visibilityOf(newUserPhone));
    }

    public void sendInvite(WebDriver driver){
        sendInviteButton().click();
        getInviteEmail().click();
        getInviteEmail().sendKeys(getProperty("user.gmail"));
        getInviteTextMessage().click();
        getInviteTextMessage().sendKeys("Welcome to PIT Service");
        getInviteSimpleUserRole().click();
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

    public boolean checkNewUser(){
        return getNewUserEmail().getText().equals(getProperty("user.gmail"));
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

    public void createNewUser(){
        getCreateNewUserButton().click();
        getCreateNewUserEmail().sendKeys(getProperty("new.user.email"));
        getCreateNewUserName().sendKeys(getProperty("new.user.fio"));
        getCreateNewUserPass().sendKeys(getProperty("new.user.password"));
        getCreateNewUserConfirm().sendKeys(getProperty("new.user.password"));
        getCreateNewUserPhone().sendKeys(getProperty("new.user.phone"));
        getCreateNewUserSimpleRole().click();
        getCreateNewUserAccept().click();
    }
    public boolean getCreatedEmail(){
        return getProperty("new.user.email").equals(getNewUserEmail().getText());
    }
    public boolean getCreatedName(){
        return getProperty("new.user.fio").equals(getNewUserName().getText());
    }
    public boolean getCreatedPhone(){
        return getProperty("new.user.phone").equals(getNewUserPhone().getText());
    }
}