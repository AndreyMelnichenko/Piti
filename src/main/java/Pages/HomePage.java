package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
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
    //--------- Check device on Home Page
    public SelenideElement firstDeviceItem(){return $(By.xpath("//span[contains(text(),'Test Device GT3101')]"));}
    public SelenideElement allarmPic(){return $(By.xpath("(//div[@aria-describedby='cdk-describedby-message-9'])[1]"));}
    public SelenideElement lockPic(){return $(By.xpath("(//div[@aria-describedby='cdk-describedby-message-9'])[1]"));}
    public SelenideElement speedPic(){return $(By.xpath("(//div[@aria-describedby='cdk-describedby-message-9'])[3]"));}
    public SelenideElement infoPic(){return $(By.xpath("//div[@class='devicesCategory_item-infoIcon']"));}
    public SelenideElement showInMap(){return $(By.xpath("//div[@class='checkbox_icon checked']"));}
    public SelenideElement firstActiveFilterRightWidget(){return $(By.xpath("(//div[@class='mapWiget_filter-item'])[1]"));}


}
