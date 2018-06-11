package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SelenideRegistration {
    public SelenideElement emailField(){return $(By.xpath("//input[@name='email']"));}
    public SelenideElement passwordField(){return $(By.xpath("//input[@name='password']"));}
    public SelenideElement passwordConfirmField(){return $(By.xpath("//input[@name='passwordConfirm']"));}
    public SelenideElement buttonCreate(){return $(By.xpath("//button[@type='submit']"));}
    public SelenideElement errorMessage(){return $(By.xpath("//span[@class='err']"));}
}
