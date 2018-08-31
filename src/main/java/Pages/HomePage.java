package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.RandomMinMax;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public SelenideElement map(){
        return $(By.xpath("//div[@class='map leaflet-container leaflet-touch leaflet-fade-anim leaflet-grab leaflet-touch-drag leaflet-touch-zoom']"));
    }
    public SelenideElement logo(){return  $(By.xpath("//a[@class='header_logo']"));}
    public SelenideElement menu(){
        return $(By.xpath("//div[@class='menuBtn']"));
    }
    public SelenideElement exit(){
        return $(By.xpath("(//div[@class='menu_item-label'])[last()]"));
    }
    public SelenideElement accountSettings(){
        return $(By.xpath("(//div[@class='menu_item-label'])[2]"));
    }
    public SelenideElement userSettingsPage(){return  $(By.xpath("(//a[@class='menu_item'])[3]"));}
    //--------- Check device on Home Page
    public SelenideElement firstDeviceItem(){return $(By.xpath("//span[contains(text(),'Test Device GT3101')]"));}
    public SelenideElement allarmPic(){return $(By.xpath("//div[@class='stateInfo_item alarm__off']"));}
    public SelenideElement lockPic(){return $(By.xpath("//div[@class='stateInfo_item lock__off']"));}
    public SelenideElement speedPic(){return $(By.xpath("//div[@class='stateInfo_item speed__off']"));}
    public SelenideElement infoPic(){return $(By.xpath("//div[@class='devicesCategory_item-infoIcon']"));}
    public SelenideElement showInMap(){return $(By.xpath("//div[@class='checkbox_label' and contains(text(),'Отображать на карте')]"));}
    public SelenideElement firstActiveFilterRightWidget(){return $(By.xpath("(//div[@class='mapWiget_filter-item'])[1]"));}
    public SelenideElement firstDeviceArea(){return $(By.xpath("//div[@class='item-box']"));}//devicesCategory_
    public SelenideElement firstDeviceAreaSelected(){return $(By.xpath("//div[@class='item-box selected']"));}
    public SelenideElement firstDeviceName(){return $(By.xpath("//div[@class='devicesCategory_item-name']/span"));}
    public SelenideElement firstDeviceLastUpdate(){return $(By.xpath("(//div[@class='devicesCategory_item-date'])[1]/span"));}
    //---------- Settings
    public SelenideElement settings(){return $(By.xpath("//a[@href='/home/settings']"));} //span[contains(text(),'НАСТРОЙКИ')]
    //----------Map
    public SelenideElement carOnMap(){return $(By.xpath("//div[@class='pit-marker-car']"));}
    public SelenideElement carOnMapDescription(){return $(By.xpath("//div[@class='mapCarModal_name']"));}
    public SelenideElement mapZoomOut(){return $(By.xpath("//a[@class='leaflet-control-zoom-out']"));}
    public SelenideElement mapSettings(){return $(By.xpath("//div[@class='settings']"));}
    public SelenideElement pointA(){return $(By.xpath("(//div[@class='marker_extremePoint'])[1]"));}
    public SelenideElement pointB(){return $(By.xpath("(//div[@class='marker_extremePoint'])[2]"));}
    public SelenideElement popUpPointA(){return $(By.xpath("(//div[@class='extreme_modal'])[1]"));}
    public SelenideElement popUpPointB(){return $(By.xpath("(//div[@class='extreme_modal'])[2]"));}
    //---------Calendar
    public SelenideElement calendarPeriod(){return $(By.xpath("//a[@class='subMenu_item subMenu_item-calendar']"));}
    public SelenideElement calendarPrev(){return $(By.xpath("//button[@title='Previous month']"));}
    public SelenideElement calendarNext(){return $(By.xpath("//button[@title='Next month']"));}
    public SelenideElement startDate(){return $(By.xpath("(//span[@class='custom-day' and contains(text(),' "+RandomMinMax.Go(1,30) +" ')])[1]"));}
    public SelenideElement endDate(){return $(By.xpath("(//span[@class='custom-day' and contains(text(),' 5 ')])[2]"));}
    public SelenideElement calendarHeadFirst(){return $(By.xpath("//div[@class='head-left-bottom']"));}
    public SelenideElement calendarHeadSecond(){return $(By.xpath("//div[@class='head-right-bottom']"));}
    public SelenideElement applyPeriod(){return $(By.xpath("//div[@class='btnRange' and contains(text(), 'ок')]"));}
    public SelenideElement chosedPeriod(){return $(By.xpath("//app-map-date-widget/span"));}
    public SelenideElement createDeviceGroup(){return $(By.xpath("//button[@class='devices_btns-addCategory']"));}
    //---------Create Group
    public SelenideElement addGroupPopUpTitle(){return $(By.xpath("//span[contains(text(),'Добавить группу')]"));}
    public SelenideElement groupName(){return $(By.xpath("//input[@id='creatCategoryName']"));}
    public SelenideElement acceptCreateGroup(){return $(By.xpath("//button[@type='submit' and contains(text(),'ПОДТВЕРДИТЬ')]"));}
    public SelenideElement editGroup(){return $(By.xpath("//div[@class='label_group']/button[@type='button']"));}
    public SelenideElement inputNewGroupName(){return $(By.xpath("//div[@class='EditLabel_group']/input[@type='text']"));}
    public SelenideElement acceptNewGroupName(){return $(By.xpath("//div[@class='editBtns']/button[1]"));}
    public SelenideElement deleteNewGroupName(){return $(By.xpath("//div[@class='editBtns']/button[2]"));}
    public SelenideElement deleteNewGroupPopUpTitle(){return $(By.xpath("//div[@class='creatCategory_top']/span[contains(text(),'Удалить группу?')]"));}
    public SelenideElement acceptDeleteNewGroup(){return $(By.xpath("(//button[@class='creatCategory_accept' and contains(text(),'ПОДТВЕРДИТЬ')])[2]"));}
    //----------Right wiget
    public SelenideElement lastTrip(){return $(By.xpath("(//div[@class='card_top-schemeMin'])[1]"));}
}
