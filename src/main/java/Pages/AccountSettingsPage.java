package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.CustomWait;
import utils.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static utils.PropertiesCache.getProperty;

public class AccountSettingsPage extends Util {
    public AccountSettingsPage(WebDriver webDriver) {
        super(webDriver);
    }

    private static String name, email, number;

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
    @FindBy(how = How.XPATH, using = "(//div[@class='user_info-mail'])[1]")
    private WebElement firstUserEmail;
    @FindBy(how = How.XPATH, using = "(//div[@class='user_info-mail'])[2]")
    private WebElement newUserEmail; //second user position
    @FindBy(how = How.XPATH, using = "(//div[@class='user_info-name'])[2]")
    private WebElement newUserName;
    @FindBy(how = How.XPATH, using = "(//div[@class='user_info-name'])[1]")
    private WebElement firstUserName;
    @FindBy(how = How.XPATH, using = "(//div[@class='user_info-phone'])[2]")
    private WebElement newUserPhone;
    @FindBy(how = How.XPATH, using = "(//div[@class='user_info-phone'])[1]")
    private WebElement firstUserPhone;
    //------------------
    @FindBy(how = How.XPATH, using = "(//input[@name='email'])[2]")
    private WebElement createNewUserEmail;
    @FindBy(how = How.XPATH, using = "(//input[@name='email'])[3]")
    private WebElement reSetUpUserEmail;
    @FindBy(how = How.XPATH, using = "(//div[@class='bottom'])/div[2]")
    private WebElement createNewUserButton;
    @FindBy(how = How.XPATH, using = "(//span[@class='checkmark'])[6]")
    private WebElement createNewUserSimpleRole;
    @FindBy(how = How.XPATH, using = "(//input[@name='name'])[1]")
    private WebElement createNewUserName;
    @FindBy(how = How.XPATH, using = "(//input[@name='name'])[2]")
    private WebElement reSetUpUserName;
    @FindBy(how = How.XPATH, using = "(//input[@name='password'])[1]")
    private WebElement createNewUserPass;
    @FindBy(how = How.XPATH, using = "(//input[@name='passwordConfirm'])[1]")
    private WebElement createNewUserConfirm;
    @FindBy(how = How.XPATH, using = "(//input[@name='phone'])[1]")
    private WebElement createNewUserPhone;
    @FindBy(how = How.XPATH, using = "(//input[@name='phone'])[2]")
    private WebElement reSetUpUserPhone;
    @FindBy(how = How.XPATH, using = "(//button[@class='addUser_accept'])[2]")
    private WebElement createNewUserAccept;
    @FindBy(how = How.XPATH, using = "//button[@class='edit_accept' and @type='submit']")
    private WebElement reSetUpUserAccept;

    //-----------------

    @FindBy(how = How.XPATH, using = "(//div[@class='droprown_btn'])[1]")
    private WebElement threeDotsButton;
    @FindBy(how = How.XPATH, using = "(//div[@class='droprown_item'])[1]")
    private WebElement dropDownSetUp;
    @FindBy(how = How.XPATH, using = "//select[@id='timezone']")
    private WebElement timezone;
    @FindBy(how = How.XPATH, using = "//option[@value='+2']")
    private WebElement secondTimeZone;

    @FindBy(how = How.XPATH, using = "//div[@class='menu_item-label' and contains(text(),'Устройства')]")
    private WebElement devices;

    //-----------------
    private WebElement getDevices(){
        return waitFor(ExpectedConditions.visibilityOf(devices));
    }

    private WebElement getReSetUpUserAccept(){
        return waitFor(ExpectedConditions.visibilityOf(reSetUpUserAccept));
    }

    private WebElement getFirstUserName(){
        return waitFor(ExpectedConditions.visibilityOf(firstUserName));
    }

    private WebElement getTimezone(){
        return waitFor(ExpectedConditions.visibilityOf(timezone));
    }

    private WebElement getReSetUpUserPhone(){
        return waitFor(ExpectedConditions.visibilityOf(reSetUpUserPhone));
    }

    private WebElement getReSetUpUserName(){
        return waitFor(ExpectedConditions.visibilityOf(reSetUpUserName));
    }

    private WebElement getReSetUpUserEmail(){
        return waitFor(ExpectedConditions.visibilityOf(reSetUpUserEmail));
    }

    private WebElement getDropDownSetUp(){
        return waitFor(ExpectedConditions.visibilityOf(dropDownSetUp));
    }

    private WebElement getThreeDotsButton(){
        return waitFor(ExpectedConditions.visibilityOf(threeDotsButton));
    }

    private WebElement getFirstUserEmail(){
        return waitFor(ExpectedConditions.visibilityOf(firstUserEmail));
    }

    private WebElement getFirstUserPhone(){
        return waitFor(ExpectedConditions.visibilityOf(firstUserPhone));
    }

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
        Actions action = new Actions(driver);
        action.moveToElement(getAcceptSendInvite()).perform();
        action.moveToElement(getAcceptSendInvite()).click().perform();
        CustomWait.getTwoSecondWait();
    }

    public boolean checkNewUser(){
        return getNewUserEmail().getText().equals(getProperty("user.gmail"));
    }

    public void goExit(){
        CustomWait.getOneSecondWait();
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

    public boolean changeUserData(){
        name=getFirstUserName().getText();
        getThreeDotsButton().click();
        getDropDownSetUp().click();
        getReSetUpUserEmail().clear();
        getReSetUpUserEmail().sendKeys(getProperty("user.email"));
        getReSetUpUserName().clear();
        String newName = "Dima"+new SimpleDateFormat("_dd-MM-yyyy_HH:mm").format(Calendar.getInstance().getTime());
        getReSetUpUserName().sendKeys(newName);
        getReSetUpUserPhone().clear();
        getReSetUpUserPhone().sendKeys(getProperty("new.user.phone"));
        Select timezone = new Select(getTimezone());
        timezone.selectByVisibleText("Киев +2");
        getReSetUpUserAccept().click();
        CustomWait.getOneSecondWait();
        return name.equals(getFirstUserName().getText());
    }

    public void goDevices(){
        getDevices().click();
    }
}