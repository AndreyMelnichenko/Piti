package Gmail;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MailHelloPage {

    public SelenideElement goEnter(){return $(By.xpath("//a[contains(text(),'Войти')]"));}
}
