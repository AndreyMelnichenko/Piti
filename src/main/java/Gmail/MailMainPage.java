package Gmail;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MailMainPage {
    public SelenideElement titleInvite (){return $(By.xpath("(//span[@class='bqe' and contains(text(), 'Signup invite')])[2]"));}
    public SelenideElement titleConfirm (){return $(By.xpath("(//span[@class='bqe' and contains(text(), 'Signup Confirmation')])[2]"));}
    public SelenideElement titleReset (){return $(By.xpath("(//span[@class='bqe' and contains(text(), 'Password reset for My Application')])[2]"));}
    public SelenideElement checkbox(){return $(By.xpath("//span[@role='checkbox']"));}
    public SelenideElement delteLetters(){return $(By.xpath("//div[@role='button' and @act='10']"));}


}
