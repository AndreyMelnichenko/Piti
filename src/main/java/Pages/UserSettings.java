package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;

/**
 * created by Andrey Melnichenko at 17:53 20-08-2018
 */
public class UserSettings {
    //------Selectors--------
    private SelenideElement timezone(){return $(By.xpath("//select[@name='timezone']"));}
    private SelenideElement saveButton(){return $(By.xpath("//div[@class='saveSettings __active']"));}
    private SelenideElement goToMainPage(){return $(By.xpath("//img[@src='/assets/img/logo.png']"));}
    //------Methods----------
    public int chooseTimeZone(){
        Select selectDevice = new Select(timezone());
        selectDevice.selectByValue("3");
        return Integer.parseInt(selectDevice.getFirstSelectedOption().getAttribute("value"));
    }
    public void goMainPage(){
        goToMainPage().waitUntil(Condition.visible,5000).click();
    }
    public void saveSettings(){
        saveButton().waitUntil(Condition.visible,5000).click();
        System.out.println("Saved");
    }
}
