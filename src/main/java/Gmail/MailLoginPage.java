package Gmail;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MailLoginPage {
    public SelenideElement inputEmail(){return $(By.xpath("//input[@type='email']"));}
    public SelenideElement next(){return $(By.xpath("(//span[@class='RveJvd snByac'])[2]"));}
}
