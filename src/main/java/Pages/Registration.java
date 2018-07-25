package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Registration {
    public SelenideElement emailField(){return $(By.xpath("//input[@name='email']"));}
    public SelenideElement passwordField(){return $(By.xpath("//input[@name='password']"));}
    public SelenideElement passwordConfirmField(){return $(By.xpath("//input[@name='passwordConfirm']"));}
    public SelenideElement buttonCreate(){return $(By.xpath("//button[@type='submit']"));}
    public SelenideElement errorMessageEmail(){return $(By.xpath("(//span[@class='err'])[1]"));}
    public SelenideElement errorMessageTimeZone(){return $(By.xpath("(//span[@class='err'])[3]"));}
    public SelenideElement listTimeZone(){return $(By.xpath("//div[@class='inp_wrap']/select"));}
}
