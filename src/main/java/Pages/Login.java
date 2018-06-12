package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Login {

    public SelenideElement errorLogin(){return $(By.xpath("(//span[@class='err'])[1]"));}
    public SelenideElement errorPassword(){return $(By.xpath("(//span[@class='err'])[2]"));}
    public SelenideElement title(){return $(By.xpath("//div[@class='auth_title']"));}
    public SelenideElement logo(){
        return $(By.xpath("//img"));
    }
    public SelenideElement login(){
        return $(By.xpath("//input[@name='email']"));
    }
    public SelenideElement password(){
        return $(By.xpath("//input[@name='password']"));
    }
    public SelenideElement enter(){
        return $(By.xpath("//button[@class='login']"));
    }
    public SelenideElement recoverPass(){return $(By.xpath("//a[@class='forget']"));}
    public SelenideElement registration(){return $(By.xpath("//a[@href='/register']"));}
}
