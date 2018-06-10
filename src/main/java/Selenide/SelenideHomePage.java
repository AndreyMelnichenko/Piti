package Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class SelenideHomePage {
    SelenideElement map(){
        return $(By.xpath("//div[@class='map leaflet-container leaflet-touch leaflet-fade-anim leaflet-grab leaflet-touch-drag leaflet-touch-zoom']"));
    }
    SelenideElement menu(){
        return $(By.xpath("//div[@class='menuBtn']"));
    }
    SelenideElement exit(){
        return $(By.xpath("(//div[@class='menu_item-label'])[last()]"));
    }
    SelenideElement accountSettings(){
        return $(By.xpath("(//div[@class='menu_item-label'])[2]"));
    }

}
