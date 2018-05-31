package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.LinkResponse;
import utils.Util;

public class ErrorPage extends Util {
    public ErrorPage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(how = How.XPATH, using = "//a")
    private WebElement goMainPageButton;
    @FindBy(how = How.XPATH, using = "//div[@class='head']")
    private WebElement title;

    public WebElement getGoMainPageButton(){
        WebElement element = waitFor(ExpectedConditions.elementToBeClickable(goMainPageButton));
        return element;
    }

    public WebElement getTitle(){
        return waitFor(ExpectedConditions.visibilityOf(title));
    }

    public int checkGoMainPageLinkResponseCode(){
        getGoMainPageButton().isDisplayed();
        return LinkResponse.getCode(getGoMainPageButton().getAttribute("href"));
    }

    public String getTitleText(){
        return getTitle().getText();
    }

}
