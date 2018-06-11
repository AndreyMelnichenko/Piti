package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SelenideHomePage {
    public SelenideElement map(){
        return $(By.xpath("//div[@class='map leaflet-container leaflet-touch leaflet-fade-anim leaflet-grab leaflet-touch-drag leaflet-touch-zoom']"));
    }
    public SelenideElement menu(){
        return $(By.xpath("//div[@class='menuBtn']"));
    }
    public SelenideElement exit(){
        return $(By.xpath("(//div[@class='menu_item-label'])[last()]"));
    }
    public SelenideElement accountSettings(){
        return $(By.xpath("(//div[@class='menu_item-label'])[2]"));
    }

}
