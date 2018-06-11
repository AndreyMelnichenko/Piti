package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SelenideErrPage {
    public SelenideElement errTitle(){
        return $(By.xpath("//div[@class='head']"));
    }
}
