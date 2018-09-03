package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Settings {
    public SelenideElement setViews(){return $(By.xpath("//a[@href='/home/settings/views']"));}
    public SelenideElement thirdIco(){return $(By.xpath("(//div[@class='icons_list-item standart_ico'])[3]"));}
    public SelenideElement currentIco(){return $(By.xpath("//div[@class='icons_list-item __avatar_ico __current']"));}
    public SelenideElement saveButton(){return $(By.xpath("//div[@class='saveSettings __active']"));}
    public SelenideElement customIcon(){return $(By.xpath("//div[@class='icons_list-item __avatar_ico']"));}
    public SelenideElement deviceIcon(){return $(By.xpath("//div[@class='devicesCategory_item-img']//*[name()='svg']//*[name()='use' and @*='#avatar-ico-3']"));}
    public SelenideElement deleteIcon(){return $(By.xpath("//div[@class='delete_photo']"));}
    public SelenideElement uploadIcon(){return $(By.xpath("//label[@class='fileIco_label']"));}
    public SelenideElement uploadInput(){return $(By.xpath("//input[@class='icons_list-item upload_inp']"));}
    public SelenideElement acceptUpload(){return $(By.xpath("//button[@class='creatCategory_accept' and contains(text(), 'СОХРАНИТЬ')]"));}
    public SelenideElement settingsDevice(){return $(By.xpath("//a[@href='/home/settings/parameters']"));}
    public SelenideElement deviceCurrentName(){return $(By.xpath("//input[@placeholder='Название устройства' and @class='inp ng-untouched ng-pristine ng-valid']"));}
    //public SelenideElement deviceClearInput(){return $(By.xpath("//form[@class='ng-invalid ng-dirty ng-touched']//input[@placeholder='Название устройства']"));}
    //public SelenideElement deviceClearInput(){return $(By.cssSelector("input.inp.ng-dirty"));}
    public SelenideElement deviceOldName(){return $(By.xpath("//input[@placeholder='Название устройства' and @class='inp ng-valid ng-dirty ng-touched']"));}
    public SelenideElement usersItem(){return $(By.xpath("//a[contains(text(), 'Пользователи')]"));}
}