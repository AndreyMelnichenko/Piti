package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import static org.testng.Assert.assertFalse;

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
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Test Device GT3101')]")
    private WebElement firstDevice;
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
    //------------------------------DeviceItem
    @FindBy(how = How.XPATH, using = "(//div[@aria-describedby='cdk-describedby-message-9'])[1]")
    private WebElement allarmPic;
    @FindBy(how = How.XPATH, using = "(//div[@aria-describedby='cdk-describedby-message-9'])[2]")
    private WebElement lockmPic;
    @FindBy(how = How.XPATH, using = "(//div[@aria-describedby='cdk-describedby-message-9'])[3]")
    private WebElement speedPic;
    @FindBy(how = How.XPATH, using = "//div[@class='devicesCategory_item-infoIcon']")
    private WebElement infoPic;
    //------------------------------Map
    @FindBy(how = How.XPATH, using = "//div[@class='checkbox_icon checked']")
    private WebElement showInMap;
    @FindBy(how = How.XPATH, using = "//div[@class='pit-marker-car']")
    private WebElement carPic;
    @FindBy(how = How.XPATH, using = "//a[@class='leaflet-control-zoom-out']")
    private WebElement zoomOut;
    @FindBy(how = How.XPATH, using = "//div[@class='settings']")
    private WebElement mapSettings;
    @FindBy(how = How.XPATH, using = "//a[@class='subMenu_item']")
    private WebElement periodCalendar;
    @FindBy(how = How.XPATH, using = "(//td[@data-title='r2c3'])[1]")
    private WebElement startDate;
    @FindBy(how = How.XPATH, using = "(//td[@data-title='r4c2'])[2]")
    private WebElement endDate;
    @FindBy(how = How.XPATH, using = "(//th[@class='prev available'])")
    private WebElement prevMonth;
    @FindBy(how = How.XPATH, using = "(//th[@class='next available'])")
    private WebElement nextMonth;
    @FindBy(how = How.XPATH, using = "(//button[@class='applyBtn btn btn-sm btn-success'])")
    private WebElement applyPeriod;
    @FindBy(how = How.XPATH, using = "//app-sub-menu/span")
    private WebElement choosedDate;
    //--------------------------------Wget
    @FindBy(how = How.XPATH, using = "(//div[@class='mapWiget_filter-item'])[1]")
    private WebElement periodicalData;
    @FindBy(how = How.XPATH, using = "(//div[@class='mapWiget_filter-item'])[2]")
    private WebElement geoData;
    @FindBy(how = How.XPATH, using = "(//div[@class='mapWiget_filter-item'])[3]")
    private WebElement securityMode;
    @FindBy(how = How.XPATH, using = "(//div[@class='mapWiget_filter-item'])[4]")
    private WebElement allarm;
    @FindBy(how = How.XPATH, using = "(//div[@class='mapWiget_filter-item'])[5]")
    private WebElement power;
    @FindBy(how = How.XPATH, using = "(//div[@class='mapWiget_filter-item'])[6]")
    private WebElement notify;
    @FindBy(how = How.XPATH, using = "(//div[@class='mapWiget_filter-item'])[7]")
    private WebElement ballance;

    private WebElement getAllarmPic(){
        return waitFor(ExpectedConditions.visibilityOf(allarmPic));
    }

    private WebElement getFirstDevice(){
        return waitFor(ExpectedConditions.visibilityOf(firstDevice));
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

    public void checkDeviceItem(WebDriver driver){
        getFirstDevice().click();
        CustomWait.getMinWait();
        getFirstDevice().click();
        CustomWait.getMinWait();
        getFirstDevice().click();
        Actions builder = new Actions(driver);
        for(int i=0; i<1; i++) {
            builder.moveToElement(allarmPic).perform();
            CustomWait.getMinWait();
            builder.moveToElement(lockmPic).perform();
            CustomWait.getMinWait();
            builder.moveToElement(speedPic).perform();
            CustomWait.getMinWait();
        }
        builder.moveToElement(infoPic).perform();
        CustomWait.getMinWait();
        builder.moveToElement(showInMap).click().perform();

    }

    public void checkCarOnMap(WebDriver driver){
        Actions builder = new Actions(driver);
        builder.moveToElement(carPic).perform();
        for(int i=0; i<=2;i++) {
            builder.moveToElement(zoomOut).click().perform();
            CustomWait.getMinWait();
        }
        builder.moveToElement(mapSettings).click().perform();
    }

    public void checkWiget(WebDriver driver){
        Actions builder = new Actions(driver);
        for(int i=0; i<7;i++) {
            builder.moveToElement(periodicalData).perform();
            builder.moveToElement(periodicalData).click().perform();
        }
    }
    public void setPeriodCalendar(WebDriver driver){
        String currentText=choosedDate.getText();
        Actions builder = new Actions(driver);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", periodCalendar);
        builder.moveToElement(prevMonth).click().perform();
        builder.moveToElement(startDate).click().perform();
        builder.moveToElement(nextMonth).click().perform();
        builder.moveToElement(endDate).click().perform();
        builder.moveToElement(applyPeriod).click().perform();
        System.out.println(choosedDate.getText());
        assertFalse(currentText.equals(choosedDate.getText()));
    }
}
