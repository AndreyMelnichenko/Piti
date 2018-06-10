package Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SelenideRegistration {
    SelenideElement emailField(){return $(By.xpath("//input[@name='email']"));}
    SelenideElement passwordField(){return $(By.xpath("//input[@name='password']"));}
    SelenideElement passwordConfirmField(){return $(By.xpath("//input[@name='passwordConfirm']"));}
    SelenideElement buttonCreate(){return $(By.xpath("//button[@type='submit']"));}
}
