package Gmail;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.WebDriverTestBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MailLetterPage extends WebDriverTestBase {
    public ElementsCollection pitLogo(){return  $$(By.xpath("//img[@alt='Pit logo']"));}

    public SelenideElement mailLink(){return $(By.xpath("//td/a[@style='display:block;width:270px;height:36px']"));}
    public SelenideElement firstText(){return $(By.xpath("//td[contains(text(), 'Здравствуйте,')]"));}
    public SelenideElement email(){return $(By.xpath("//td[contains(text(), 'Здравствуйте,')]/a"));}
    public SelenideElement backToMailList(){return $(By.xpath("//div[@class='T-I J-J5-Ji lS T-I-ax7   mA']"));}//div[@class='ar6 T-I-J3 J-J5-Ji']"));}
    //------------ Cinfirm registration
    public SelenideElement confirmRegistration(){return $(By.xpath("//img[@alt='link registration']"));}
    public SelenideElement confirmText2(){return $(By.xpath("//td[contains(text(), 'Вы отправили запрос на подтверждение адреса электронной почты.')]"));}
    public SelenideElement confirmText3(){return $(By.xpath("//td[contains(text(), 'Благодаря подтверждению адреса электронной почты защита вашей учетной записи PIT усиливается и вы получаете доступ к системе рассылок.')]"));}
    //------------ invite registration
    public SelenideElement beginRegistration(){return $(By.xpath("//img[@alt='link begin']"));}
    public SelenideElement secondText(){return $(By.xpath("//td[contains(text(), 'Вам отправили приглашение на регистрацию. ')]"));}
    public SelenideElement thirdText(){return $(By.xpath("//td[contains(text(), 'Если вы не собирались регистрироваться, просто удалите это письмо.')]"));}
    //------------ recover
    public SelenideElement recoverPass (){return $(By.xpath("//img[@alt='link recover']"));}
    public SelenideElement resetText2(){return $(By.xpath("//td[contains(text(), 'Вы отправили запрос на восстановление пароля. ')]"));}
    public SelenideElement resetText3(){return $(By.xpath("//td[contains(text(), 'После этого введите новый пароль. Если вы не собирались менять пароль, просто удалите это письмо.')]"));}
    //------------ last text
    public SelenideElement endText(){return $(By.xpath("//td[contains(text(), 'С уважением, команда PIT.')]"));}
}
