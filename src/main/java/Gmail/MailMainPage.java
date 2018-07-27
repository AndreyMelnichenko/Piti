package Gmail;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MailMainPage {
    public ElementsCollection title (){return $$(By.xpath("//span[@class='bog']"));}
    public SelenideElement checkbox(){return $(By.xpath("//span[@role='checkbox']"));}
    public SelenideElement delteLetters(){return $(By.xpath("//div[@role='button' and @act='10']"));}


}
