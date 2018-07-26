package Gmail;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class MailMainPage {
    public ElementsCollection title (){return $$(By.xpath("//span[@class='bog']"));}


}
