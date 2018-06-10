package Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SelenideAccountSettings {
    SelenideElement createNewUserButton(){return $(By.xpath("(//div[@class='bottom'])/div[2]"));}
    SelenideElement emailNewUser(){return $(By.xpath("(//input[@name='email'])[2]"));}
    SelenideElement nameNewUser(){return $(By.xpath("(//input[@name='name'])[1]"));}
    SelenideElement passNewUser(){return $(By.xpath("(//input[@name='password'])[1]"));}
    SelenideElement passNewUserConfirm(){return $(By.xpath("(//input[@name='passwordConfirm'])[1]"));}
    SelenideElement phoneNewUser(){return $(By.xpath("(//input[@name='phone'])[1]"));}
    SelenideElement roleNewUser(){return $(By.xpath("(//span[@class='checkmark'])[6]"));}
    SelenideElement acceptCreateNewUser(){return $(By.xpath("(//button[@class='addUser_accept'])[2]"));}
}
