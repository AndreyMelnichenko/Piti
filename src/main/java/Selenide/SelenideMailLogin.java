package Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class SelenideMailLogin {
    SelenideElement emailSelenide(){
        return $(By.xpath("//input[@type='email']"));
    }
    SelenideElement singIn(){
        return $(By.xpath("//a[@data-g-label='Sign in']"));
    }
    SelenideElement nextButton(){
        return $(By.xpath("(//span[@class='RveJvd snByac'])[3]"));
    }
}
