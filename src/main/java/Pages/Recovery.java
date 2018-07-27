package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Recovery {
    public SelenideElement title(){return $(By.xpath("//div[@class='auth_title']"));}
    public SelenideElement resetTitle(){return $(By.xpath("//div[@class='auth_title']"));}
    public SelenideElement emailField(){return $(By.xpath("//input[@formcontrolname='email']"));}
    public SelenideElement recoveryButton(){return $(By.xpath("//button[@class='recoverBtn']"));}
    public SelenideElement textArea(){return $(By.xpath("//div[@class='box']/p[1]"));}
}
