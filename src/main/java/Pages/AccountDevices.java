package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.CustomWait;
import utils.DataProperties;
import utils.Util;

public class AccountDevices extends Util {
    public AccountDevices(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'ДОБАВИТЬ УСТРОЙСТВО')]")
    private WebElement addelementButton;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Название устройства']")
    private WebElement deviceName;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Imei устройства']")
    private WebElement deviceImei;
    @FindBy(how = How.XPATH, using = "//select[@formcontrolname='type']")
    private WebElement deviceType;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='телефон']")
    private WebElement devicePhone;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='access']")
    private WebElement deviceAccess;
    @FindBy(how = How.XPATH, using = "//div[@class='show_pass isVisible']")
    private WebElement showPass;
    @FindBy(how = How.XPATH,using = "//input[@placeholder='Apn']")
    private WebElement deviceApn;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'ОТПРАВИТЬ')]")
    private WebElement submitForm;
    @FindBy(how = How.XPATH, using = "(//div[@class='content_item'])[1]")
    private WebElement createdDevice;
    @FindBy(how = How.XPATH, using = "(//div[@class='droprown_btn'])[1]")
    private WebElement threeDotsButton;
    @FindBy(how = How.XPATH, using = "(//div[@class='droprown_item'])[1]")
    private WebElement dropDownDelete;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'ПОДТВЕРДИТЬ')]")
    private WebElement confirmRemoveDevice;
    //-----------
    @FindBy(how = How.XPATH, using = "//div[@class='menuBtn_box']")
    private WebElement menuButton;
    @FindBy(how = How.XPATH, using = "//div[@class='menu_item']")
    private WebElement exitButton;
    //-----------
    private WebElement getConfirmRemoveDevice(){
        return waitFor(ExpectedConditions.visibilityOf(confirmRemoveDevice));
    }

    private WebElement getDropDownDelete(){
        return waitFor(ExpectedConditions.visibilityOf(dropDownDelete));
    }

    private WebElement getThreeDotsButton(){
        return waitFor(ExpectedConditions.visibilityOf(threeDotsButton));
    }

    private WebElement getCreatedDevice(){
        return waitFor(ExpectedConditions.visibilityOf(createdDevice));
    }

    private WebElement getSubmitForm(){
        return waitFor(ExpectedConditions.visibilityOf(submitForm));
    }

    private WebElement getDeviceApn(){
        return waitFor(ExpectedConditions.visibilityOf(deviceApn));
    }

    private WebElement getShowPass(){
        return waitFor(ExpectedConditions.visibilityOf(showPass));
    }

    private WebElement getDeviceAccess(){
        return waitFor(ExpectedConditions.visibilityOf(deviceAccess));
    }

    private WebElement getDevicePhone(){
        return waitFor(ExpectedConditions.visibilityOf(devicePhone));
    }

    private WebElement getDeviceType(){
        return waitFor(ExpectedConditions.visibilityOf(deviceType));
    }

    private WebElement getDeviceImei(){
        return waitFor(ExpectedConditions.visibilityOf(deviceImei));
    }

    private WebElement getDeviceName(){
        return waitFor(ExpectedConditions.visibilityOf(deviceName));
    }

    private WebElement getAddelementButton(){
        return waitFor(ExpectedConditions.visibilityOf(addelementButton));
    }

    public void clickAddDevice(){
        getAddelementButton().click();
    }

    public void CreateDevice(){
        getDeviceName().sendKeys(DataProperties.dataProperty("data.properties","TK116.name"));
        getDeviceImei().sendKeys(DataProperties.dataProperty("data.properties","TK116.imei"));
        Select selectDevice = new Select(getDeviceType());
        selectDevice.selectByVisibleText(DataProperties.dataProperty("data.properties","TK116.types"));
        getDevicePhone().sendKeys(DataProperties.dataProperty("data.properties","TK116.sim"));
        getDeviceAccess().sendKeys(DataProperties.dataProperty("data.properties","TK116.pass"));
        getShowPass().click();
        getDeviceApn().sendKeys(DataProperties.dataProperty("data.properties","TK116.apn"));
        getSubmitForm().click();
        CustomWait.getHalfSecondWait();
    }

    public boolean isNewDeviceCreated(){
        return getCreatedDevice().isDisplayed();
    }

    public void isNewDeviceRemoved(WebDriver driver){
        Actions builder = new Actions(driver);
        builder.moveToElement(threeDotsButton).click().perform();
        CustomWait.getMinWait();
        builder.moveToElement(dropDownDelete).click().perform();
        CustomWait.getMinWait();
        builder.moveToElement(confirmRemoveDevice).click().perform();
    }
    public void goExitApp(WebDriver driver){
        Actions builder = new Actions(driver);
        CustomWait.getMinWait();
        builder.moveToElement(menuButton).click().perform();
        CustomWait.getMinWait();
        builder.moveToElement(exitButton).click().perform();
    }

}
