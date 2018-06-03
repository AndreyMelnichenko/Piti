package Mail;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DataProperties;
import utils.Util;

import java.util.List;
import java.util.Optional;

public class MailMainPage extends Util {
    public MailMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//div[@class='T-Jo-auh']")
    private WebElement chooseAll;
    @FindBy(how = How.XPATH, using = "//div[@aria-label='Удалить']")
    private WebElement deleteAll;
    @FindAll(@FindBy(how = How.XPATH, using = "//span[@class='bog']/b"))
    private List<WebElement> emailTitle;
    @FindBy (how = How.XPATH, using = "//div[@aria-label='Закрыть.']")
    private WebElement closeMessage;

    private WebElement getCloseMessage(){
        return waitFor(ExpectedConditions.visibilityOf(closeMessage));
    }

    public WebElement getChooseAll(){
        WebElement element = waitFor(ExpectedConditions.elementToBeClickable(chooseAll));
        return element;
    }

    public WebElement getDeleteAll(){
        //Actions action = new Actions(driver);
        WebElement element = waitFor(ExpectedConditions.visibilityOf(deleteAll));
        WebElement visibleElement = waitFor(ExpectedConditions.elementToBeClickable(element));
        return visibleElement;
    }

    public boolean getEmailTitle(){
        Optional<WebElement> element = emailTitle.stream()
                .filter(e->e.getText().equals(DataProperties.dataProperty("data.properties","email.invite")))
                .findFirst();
        if(DataProperties.dataProperty("data.properties","email.invite").equals(element.get().getText())){
            return true;
        }else {return false;}
    }

    public void cleanEmailList(){
        getChooseAll().click();
        getDeleteAll().click();
    }

    public void alertHndle(WebDriver driver){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            //exception handling
        }
    }

    public void closeAttentionMessage(){
        if (getCloseMessage().isDisplayed()){
            getCloseMessage().click();
        }
    }
}
