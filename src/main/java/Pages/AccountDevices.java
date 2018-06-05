package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.CustomWait;
import utils.DataProperties;
import utils.Util;

import static org.testng.Assert.assertTrue;

public class AccountDevices extends Util {
    public AccountDevices(WebDriver webDriver) {
        super(webDriver);
    }
    private static String createdDeviceName = DataProperties.dataProperty("data.properties","device.names");

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

    public boolean isNewDeviceCreated(){
        getDeviceName().sendKeys(createdDeviceName);
        getDeviceImei().sendKeys(DataProperties.dataProperty("data.properties","device.imei"));
        Select selectDevice = new Select(getDeviceType());
        selectDevice.selectByVisibleText(DataProperties.dataProperty("data.properties","device.types"));
        getDevicePhone().sendKeys(DataProperties.dataProperty("data.properties","device.sim"));
        getDeviceAccess().sendKeys(DataProperties.dataProperty("data.properties","device.pass"));
        getShowPass().click();
        getDeviceApn().sendKeys(DataProperties.dataProperty("data.properties","device.apn"));
        getSubmitForm().click();
        return getCreatedDevice().isDisplayed();
    }

    public boolean isNewDeviceRemoved(){
        getThreeDotsButton().click();
        getDropDownDelete().click();
        getConfirmRemoveDevice().click();
        return getCreatedDevice().isDisplayed();
    }

}
