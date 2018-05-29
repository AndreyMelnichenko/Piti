package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

public class UserHomePage extends Util {
    public UserHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//div[@class='map leaflet-container leaflet-touch leaflet-fade-anim leaflet-grab leaflet-touch-drag leaflet-touch-zoom']")
    private WebElement map;

    public boolean isMap(){
        WebElement waitMap = waitFor(ExpectedConditions.visibilityOf(map));
        return waitMap.isDisplayed();
    }
}
