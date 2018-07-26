package Gmail;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MailLetterPage {
    public ElementsCollection pitLogo(){return  $$(By.xpath("//img[@alt='Pit logo']"));}
    public SelenideElement confirmRegistration(){return $(By.xpath("//img[@alt='link registration']"));}
    public SelenideElement beginRegistration(){return $(By.xpath("//img[@alt='link begin']"));}
    public SelenideElement email(){return $(By.xpath("//td[contains(text(), 'Здравствуйте,')]/a"));}
    public SelenideElement backToMailList(){return $(By.xpath("//div[@class='ar6 T-I-J3 J-J5-Ji']"));}
    //------------ Cinfirm registration
    public SelenideElement firstText(){return $(By.xpath("//td[contains(text(), 'Здравствуйте,')]"));}
    public SelenideElement confirmText2(){return $(By.xpath("//td[contains(text(), 'Вы отправили запрос на подтверждение адреса электронной почты.')]"));}
    public SelenideElement confirmText3(){return $(By.xpath("//td[contains(text(), 'Благодаря подтверждению адреса электронной почты защита вашей учетной записи PIT усиливается и вы получаете доступ к системе рассылок.')]"));}
    //------------ begin registration
    public SelenideElement secondText(){return $(By.xpath("//td[contains(text(), 'Вам отправили приглашение на регистрацию. ')]"));}
    public SelenideElement thirdText(){return $(By.xpath("//td[contains(text(), 'Если вы не собирались регистрироваться, просто удалите это письмо.')]"));}
    //------------ last text
    public SelenideElement fourText(){return $(By.xpath("//td[contains(text(), 'С уважением, команда PIT.')]"));}
}
