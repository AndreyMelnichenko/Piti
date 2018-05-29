package Mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

public class MailMainPage extends Util {
    public MailMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//div[@class='T-Jo-auh']")
    private WebElement chooseAll;
    @FindBy(how = How.XPATH, using = "//div[@class='ar9 T-I-J3 J-J5-Ji']")
    private WebElement deleteAll;

    public WebElement getChooseAll(){
        WebElement element = waitFor(ExpectedConditions.elementToBeClickable(chooseAll));
        return element;
    }
    public WebElement getDeleteAll(){
        WebElement element = waitFor(ExpectedConditions.elementToBeClickable(deleteAll));
        return element;
    }
}
