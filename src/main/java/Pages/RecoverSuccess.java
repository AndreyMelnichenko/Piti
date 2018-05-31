package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

import static utils.PropertiesCache.getProperty;

public class RecoverSuccess extends Util {
    public RecoverSuccess(WebDriver webDriver) {
        super(webDriver);
    }

    private By recEmail = By.xpath("//p/a");

    @FindBy(how = How.XPATH, using = "//p/a")
    private WebElement recoveredEmail;

    private WebElement getRecoveredEmail(){
        return waitFor(ExpectedConditions.visibilityOfElementLocated(recEmail));
    }

    public boolean checkRecoveredEmail(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getRecoveredEmail().getText().equals(getProperty("user.email"));
    }
}
