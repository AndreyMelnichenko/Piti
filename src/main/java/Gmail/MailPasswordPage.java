package Gmail;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

import static com.codeborne.selenide.Selenide.$;
import static utils.PropertiesCache.getProperty;

public class MailPasswordPage {
    public SelenideElement inputPass(){return $(By.xpath("//input[@type='password']"));}
    public SelenideElement next(){return $(By.xpath("//span[contains(text(), 'Next')]"));}
}