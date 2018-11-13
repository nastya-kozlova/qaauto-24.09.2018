package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;


public class RequestPasswordResetPage extends BasePage {
    private WebDriver webDriver;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userEmailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    /**
     * @param webDriver
     * method with initialization of webDriver and webElements on the page
     */
    public RequestPasswordResetPage (WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return
     * method that checks is RequestPasswordResetPage loaded
     */
    public boolean isPageLoaded() {
        return findAccountButton.isDisplayed();
    }


    /**
     * @param userEmail
     * @return
     * method that enter email field and click on findAccountButton to submit email
     */
    public PasswordResetSubmitPage findAccount(String userEmail) {
        GMailService gMailService = new GMailService();
        gMailService.connect();

        userEmailField.sendKeys(userEmail);
        findAccountButton.click();

        return new PasswordResetSubmitPage(webDriver);
    }
}

