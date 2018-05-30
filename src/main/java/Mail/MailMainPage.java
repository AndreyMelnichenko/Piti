package Mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    @FindBy(how = How.XPATH, using = "//div[@aria-label='Удалить']")
    private WebElement deleteAll;

    public WebElement getChooseAll(){
        WebElement element = waitFor(ExpectedConditions.elementToBeClickable(chooseAll));
        return element;
    }
    public WebElement getDeleteAll(WebDriver driver){
        //Actions action = new Actions(driver);
        WebElement element = waitFor(ExpectedConditions.visibilityOf(deleteAll));
        WebElement visibleElement = waitFor(ExpectedConditions.elementToBeClickable(element));
        return visibleElement;
    }
}
