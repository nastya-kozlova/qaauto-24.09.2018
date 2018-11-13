package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessfulPasswordResetPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//button[text()='Go to homepage']")
    private WebElement goToHomepageButton;

    /**
     * @param webDriver
     * Method with initialization of webDriver and webElements on the page
     */
    public SuccessfulPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return
     * method that enter goToHomeButton
     */
    public HomePage clickOnGoToHomeButton() {
        goToHomepageButton.click();
        return new HomePage(webDriver);
    }

    /**
     * @return
     * method that checks is SuccessfulPasswordResetPage loaded
     */
    public boolean isLoaded() {
        return goToHomepageButton.isDisplayed();
    }
}