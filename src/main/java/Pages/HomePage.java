package Pages;

import com.codeborne.selenide.Selenide;
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
    public SelenideElement showInMap(){return $(By.xpath("//div[@class='checkbox_label' and contains(text(),'Отображать на карте')]"));}
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
    public SelenideElement createDeviceGroup(){return $(By.xpath("//button[@class='devices_btns-addCategory']"));}
    //---------Create Group
    public SelenideElement addGroupPopUpTitle(){return $(By.xpath("//span[contains(text(),'Добавить группу')]"));}
    public SelenideElement groupName(){return $(By.xpath("//input[@id='creatCategoryName']"));}
    public SelenideElement acceptCreateGroup(){return $(By.xpath("//button[@type='submit' and contains(text(),'ПОДТВЕРДИТЬ')]"));}
    public SelenideElement editGroup(){return $(By.xpath("//div[@class='label_group']/button[@type='button']"));}
    public SelenideElement inputNewGroupName(){return $(By.xpath("//input[@class='ng-untouched ng-pristine ng-valid']"));}
    public SelenideElement acceptNewGroupName(){return $(By.xpath("//div[@class='editBtns']/button[1]"));}
    public SelenideElement deleteNewGroupName(){return $(By.xpath("//div[@class='editBtns']/button[2]"));}
    public SelenideElement deleteNewGroupPopUpTitle(){return $(By.xpath("//div[@class='creatCategory_top']/span[contains(text(),'Удалить группу?')]"));}
    public SelenideElement acceptDeleteNewGroup(){return $(By.xpath("(//button[@class='creatCategory_accept' and contains(text(),'ПОДТВЕРДИТЬ')])[2]"));}
    //--------CSS
    public SelenideElement deviceCssItem(){return $(By.cssSelector("div[class='item-box selected']"));}
    public SelenideElement dragArea(){return $(By.cssSelector("div[class='devicesCategory_content']"));}

}
