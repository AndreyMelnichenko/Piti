package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.DataProperties;
import utils.Util;

public class UserHomePage extends Util {
    public UserHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//div[@class='map leaflet-container leaflet-touch leaflet-fade-anim leaflet-grab leaflet-touch-drag leaflet-touch-zoom']")
    private WebElement map;
    @FindBy(how = How.XPATH, using = "//div[@class='menuBtn']")
    private WebElement userMenu;
    @FindBy (how = How.XPATH, using = "(//div[@class='menu_item-label'])[last()]")
    private WebElement exitButton;
    @FindBy (how = How.XPATH, using = "(//div[@class='menu_item-label'])[2]")
    private WebElement accountSettings;
    @FindBy(how = How.XPATH, using = "//button[@class='devices_btns-addSingle']")
    private WebElement addNewDeviceButton;
    //-----------------------------------------------
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


    public WebElement getSubmitForm(){
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

    private WebElement getAddNewDeviceButton(){
        return waitFor(ExpectedConditions.visibilityOf(addNewDeviceButton));
    }

    public void clickAddNewDevice(){
        getAddNewDeviceButton().click();
    }

    public boolean isMap(){
        WebElement waitMap = waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='map leaflet-container leaflet-touch leaflet-fade-anim leaflet-grab leaflet-touch-drag leaflet-touch-zoom']")));
        return waitMap.isDisplayed();
    }

    public void userMenuClick(){
        WebElement element = waitFor(ExpectedConditions.visibilityOf(userMenu));
        element.click();
    }

    public void exitHomePage(){
        WebElement element=waitFor(ExpectedConditions.visibilityOf(exitButton));
        element.click();
    }

    public void accountSettingsClick(){
        WebElement element = waitFor(ExpectedConditions.visibilityOf(accountSettings));
        element.click();
    }

    public void fillFormGt3101(){
        getDeviceName().sendKeys(DataProperties.dataProperty("data.properties","GT3101.name"));
        getDeviceImei().sendKeys(DataProperties.dataProperty("data.properties","GT3101.imei"));
        Select selectDevice = new Select(getDeviceType());
        selectDevice.selectByVisibleText(DataProperties.dataProperty("data.properties","GT3101.types"));
        getDevicePhone().sendKeys(DataProperties.dataProperty("data.properties","GT3101.sim"));
        getDeviceAccess().sendKeys(DataProperties.dataProperty("data.properties","GT3101.pass"));
        getShowPass().click();
        getDeviceApn().sendKeys(DataProperties.dataProperty("data.properties","GT3101.apn"));
        getSubmitForm().click();
    }
}
