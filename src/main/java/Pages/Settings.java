package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Settings {
    public SelenideElement setViews(){return $(By.xpath("//a[@href='/home/settings/views']"));}
    public SelenideElement thirdIco(){return $(By.xpath("(//div[@class='icons_list-item standart_ico'])[2]"));}
    public SelenideElement saveButton(){return $(By.xpath("//div[@class='saveSettings __active']"));}
    public SelenideElement customIcon(){return $(By.xpath("(//div[@class='icons_list-item_wrap'])[2]"));}
}
