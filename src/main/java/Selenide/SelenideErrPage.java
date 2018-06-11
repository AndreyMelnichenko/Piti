package Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SelenideErrPage {
    SelenideElement errTitle(){
        return $(By.xpath("//div[@class='head']"));
    }
}
