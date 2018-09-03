package Gmail;

import Pages.PagesActions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static utils.DataProperties.dataProperty;
import static utils.PropertiesCache.getProperty;

public class MailActions {
    private MailHelloPage mailHelloPage = new MailHelloPage();
    private MailLoginPage mailLoginPage = new MailLoginPage();
    private MailPasswordPage mailPasswordPage = new MailPasswordPage();
    private MailMainPage mailMainPage = new MailMainPage();
    private MailLetterPage mailLetterPage = new MailLetterPage();
    private PagesActions pagesActions = new PagesActions();
    private static String confirmLink, inviteLink, resetLink;

    public void enterToMailBox(){
        mailHelloPage.goEnter().waitUntil(Condition.visible, 5000).click();
        mailLoginPage.inputEmail().setValue(getProperty("user.gmail"));
        mailLoginPage.next().waitUntil(Condition.visible, 5000).click();
        mailPasswordPage.inputPass().setValue(getProperty("password.gmail"));
        mailPasswordPage.next().waitUntil(Condition.visible, 5000).click();
        Selenide.sleep(2000);
    }

    public void checkConfirmRegisterLetter(){
        mailMainPage.title().filterBy(text("Signup Confirmation")).get(0).waitUntil(Condition.visible, 5000).click();
        Selenide.sleep(500);
        mailLetterPage.pitLogo().get(0).waitUntil(Condition.visible, 5000);
        mailLetterPage.pitLogo().get(1).waitUntil(Condition.visible, 5000);
        mailLetterPage.confirmRegistration().waitUntil(Condition.visible, 5000);
        mailLetterPage.firstText().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "first.text"));
        mailLetterPage.confirmText2().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "confirm.text2"));
        mailLetterPage.confirmText3().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "confirm.text3"));
        mailLetterPage.endText().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "end.text"));
        confirmLink = mailLetterPage.mailLink().waitUntil(Condition.visible, 5000).getAttribute("href");
        System.out.println("Confirm letter checked!!!");
    }

    public void checkInviteLetter(){
        Selenide.sleep(500);
        mailMainPage.title().filterBy(text("Signup invite")).get(0).waitUntil(Condition.visible, 2000).click();
        mailLetterPage.pitLogo().get(0).waitUntil(Condition.visible, 5000).getAttribute("src").equals(dataProperty("data.properties","heder.link"));
        mailLetterPage.pitLogo().get(1).waitUntil(Condition.visible, 5000).getAttribute("src").equals(dataProperty("data.properties","footer.link"));
        mailLetterPage.beginRegistration().waitUntil(Condition.visible, 5000).getAttribute("src").equals(dataProperty("data.properties","begin.link"));
        mailLetterPage.firstText().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "first.text"));
        mailLetterPage.secondText().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "begin.text2"));
        mailLetterPage.thirdText().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "begin.text3"));
        mailLetterPage.endText().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "end.text"));
        inviteLink = mailLetterPage.mailLink().waitUntil(Condition.visible, 5000).getAttribute("href");
        System.out.println("Invite letter checked!!!!");
    }

    public void checkResetLetter(){
        mailMainPage.title().filterBy(text("Password reset for My Application")).get(0).waitUntil(Condition.visible, 2000).click();
        mailLetterPage.pitLogo().get(0).waitUntil(Condition.visible, 5000).getAttribute("src").equals(dataProperty("data.properties","heder.link"));
        mailLetterPage.pitLogo().get(1).waitUntil(Condition.visible, 5000).getAttribute("src").equals(dataProperty("data.properties","footer.link"));
        mailLetterPage.recoverPass().waitUntil(Condition.visible, 5000).getAttribute("src").equals(dataProperty("data.properties","recover.link"));
        mailLetterPage.firstText().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "first.text"));
        mailLetterPage.resetText2().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "reset.text2"));
        mailLetterPage.resetText3().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "reset.text3"));
        mailLetterPage.endText().waitUntil(Condition.visible, 5000).getText().equals(dataProperty("data.properties", "end.text"));
        resetLink = mailLetterPage.mailLink().waitUntil(Condition.visible, 5000).getAttribute("href");
        System.out.println("Recover password checked!!!!");
    }

    public void backToMainLetterList(){
        mailLetterPage.backToMailList().waitUntil(Condition.visible, 5000).click();
    }

    public void deleteLetters(){
        mailMainPage.checkbox().waitUntil(Condition.visible, 5000).click();
        Selenide.sleep(200);
        mailMainPage.delteLetters().waitUntil(Condition.visible, 5000).click();
        Selenide.sleep(200);
        Selenide.confirm();
    }

    public void checkLinks(){
        open(confirmLink);
        Selenide.sleep(200);
        pagesActions.checkLogoPage();
        open(inviteLink);
        Selenide.sleep(200);
        pagesActions.checkAuthorizationPage();
        open(resetLink);
        Selenide.sleep(200);
        pagesActions.checkresetPage();
    }
}
