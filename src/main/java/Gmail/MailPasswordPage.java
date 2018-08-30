package Gmail;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MailPasswordPage {
    public SelenideElement inputPass(){return $(By.xpath("//input[@type='password']"));}
    public SelenideElement next(){return $(By.xpath("//span[contains(text(), 'Weiter')]"));}
}
