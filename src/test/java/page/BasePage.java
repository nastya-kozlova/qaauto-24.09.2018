package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;


public abstract class BasePage {
    WebDriver webDriver;
    static GMailService gMailService;

    public abstract boolean isPageLoaded();

    /**
     * @param webElement
     * Method waiting until element will be clickable
     */
    public void waitUntilElementIsClickable (WebElement webElement){
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * @param webElement
     * @param timeOutInSeconds
     * Method waiting until element will be visible
     */
    public void waitUntilElementIsVisible (WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

}