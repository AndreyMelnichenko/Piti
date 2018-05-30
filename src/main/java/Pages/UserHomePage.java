package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

import java.rmi.server.ExportException;

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


    public boolean isMap(){
        WebElement waitMap = waitFor(ExpectedConditions.visibilityOf(map));
        return waitMap.isDisplayed();
    }

    public void userMenuClick(){
        userMenu.click();
    }

    public void exitHomePage(){
        WebElement element=waitFor(ExpectedConditions.visibilityOf(exitButton));
        element.click();
    }

    public void accountSettingsClick(){
        WebElement element = waitFor(ExpectedConditions.visibilityOf(accountSettings));
        element.click();
    }


}
