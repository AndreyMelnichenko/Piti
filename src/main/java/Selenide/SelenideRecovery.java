package Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SelenideRecovery {
    SelenideElement title(){return $(By.xpath("//div[@class='auth_title']"));}
    SelenideElement emailField(){return $(By.xpath("//input[@formcontrolname='email']"));}
    SelenideElement recoveryButton(){return $(By.xpath("//button[@class='recoverBtn']"));}
}
