package Gmail;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.text;
import static utils.DataProperties.dataProperty;
import static utils.PropertiesCache.getProperty;

public class MailActions {
    private MailHelloPage mailHelloPage = new MailHelloPage();
    private MailLoginPage mailLoginPage = new MailLoginPage();
    private MailPasswordPage mailPasswordPage = new MailPasswordPage();
    private MailMainPage mailMainPage = new MailMainPage();
    private MailLetterPage mailLetterPage = new MailLetterPage();

    public void enterToMailBox(){
        mailHelloPage.goEnter().shouldBe(Condition.visible).click();
        mailLoginPage.inputEmail().setValue(getProperty("user.gmail"));
        mailLoginPage.next().shouldBe(Condition.visible).click();
        mailPasswordPage.inputPass().setValue(getProperty("password.gmail"));
        mailPasswordPage.next().shouldBe(Condition.visible).click();
        Selenide.sleep(500);
    }

    public void checkConfirmLetter(){
        mailMainPage.title().filterBy(text("Signup Confirmation")).get(0).waitUntil(Condition.visible, 2000).click();
        Selenide.sleep(500);
        mailLetterPage.pitLogo().get(0).shouldBe(Condition.visible).getAttribute("src").equals(dataProperty("data.properties","heder.link"));
        mailLetterPage.pitLogo().get(1).shouldBe(Condition.visible).getAttribute("src").equals(dataProperty("data.properties","footer.link"));
        mailLetterPage.confirmRegistration().shouldBe(Condition.visible).getAttribute("src").equals(dataProperty("data.properties","conf.link"));
        mailLetterPage.firstText().shouldBe(Condition.visible).getText().equals(dataProperty("data.properties", "first.text"));
        mailLetterPage.confirmText2().shouldBe(Condition.visible).getText().equals(dataProperty("data.properties", "confirm.text2"));
        mailLetterPage.confirmText3().shouldBe(Condition.visible).getText().equals(dataProperty("data.properties", "confirm.text3"));
        mailLetterPage.firstText().shouldBe(Condition.visible).getText().equals(dataProperty("data.properties", "begin.text4"));
        System.out.println("confirm letter checked!");
    }

    public void checkInviteLetter(){
        Selenide.sleep(500);
        mailMainPage.title().filterBy(text("Signup invite")).get(0).waitUntil(Condition.visible, 2000).click();
        mailLetterPage.pitLogo().get(0).shouldBe(Condition.visible).getAttribute("src").equals(dataProperty("data.properties","heder.link"));
        mailLetterPage.pitLogo().get(1).shouldBe(Condition.visible).getAttribute("src").equals(dataProperty("data.properties","footer.link"));
        mailLetterPage.beginRegistration().shouldBe(Condition.visible).getAttribute("src").equals(dataProperty("data.properties","begin.link"));
        mailLetterPage.firstText().shouldBe(Condition.visible).getText().equals(dataProperty("data.properties", "first.text"));
        mailLetterPage.secondText().shouldBe(Condition.visible).getText().equals(dataProperty("data.properties", "begin.text2"));
        mailLetterPage.thirdText().shouldBe(Condition.visible).getText().equals(dataProperty("data.properties", "begin.text3"));
        mailLetterPage.firstText().shouldBe(Condition.visible).getText().equals(dataProperty("data.properties", "begin.text4"));
        System.out.println("Invite letter checked!!!!");
    }

    public void backToMainLetterList(){
        mailLetterPage.backToMailList().shouldBe(Condition.visible).click();
    }

    public void deleteLetters(){
        mailMainPage.checkbox().shouldBe(Condition.visible).click();
        Selenide.sleep(200);
        mailMainPage.delteLetters().shouldBe(Condition.visible).click();
    }

    public void checkRegisterLink(){
        mailMainPage.title().filterBy(text("Signup Confirmation")).get(0).waitUntil(Condition.visible, 2000).click();
        mailLetterPage.confirmLink().shouldBe(Condition.visible).click();
    }
}
