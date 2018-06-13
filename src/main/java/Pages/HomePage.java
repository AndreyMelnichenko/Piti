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
    //----------Map
    public SelenideElement carOnMap(){return $(By.xpath("//div[@class='pit-marker-car']"));}
    public SelenideElement carOnMapDescription(){return $(By.xpath("//div[@class='mapCarModal_name']"));}
    public SelenideElement mapZoomOut(){return $(By.xpath("//a[@class='leaflet-control-zoom-out']"));}
    public SelenideElement mapSettings(){return $(By.xpath("//div[@class='settings']"));}
    //---------Calendar
    public SelenideElement calendarPeriod(){return $(By.xpath("//a[@class='subMenu_item']"));}
    public SelenideElement calendarPrev(){return $(By.xpath("(//th[@class='prev available'])"));}
    public SelenideElement calendarNext(){return $(By.xpath("(//th[@class='next available'])"));}
    public SelenideElement startDate(){return $(By.xpath("(//td[@data-title='r2c3'])[1]"));}
    public SelenideElement endDate(){return $(By.xpath("(//td[@data-title='r4c2'])[2]"));}
    public SelenideElement calendarHeadFirst(){return $(By.xpath("(//div[@class='calendar_head'])[1]"));}
    public SelenideElement calendarHeadSecond(){return $(By.xpath("(//div[@class='calendar_head'])[2]"));}
    public SelenideElement applyPeriod(){return $(By.xpath("(//button[@class='applyBtn btn btn-sm btn-success'])"));}
    public SelenideElement chosedPeriod(){return $(By.xpath("//app-sub-menu/span"));}
}
