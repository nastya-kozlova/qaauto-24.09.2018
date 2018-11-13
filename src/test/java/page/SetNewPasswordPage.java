package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SetNewPasswordPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;


    /**
     * @param webDriver
     * Method with initialization of webDriver and webElements on the page
     */
    public SetNewPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @param newUserPassword
     * @return
     * method that enter newPasswordField and submit it by clicking on submitButton. That opens SuccessfulPasswordResetPage.
     */
    public SuccessfulPasswordResetPage submitNewPassword(String newUserPassword) {
        newPasswordField.sendKeys(newUserPassword);
        confirmPasswordField.sendKeys(newUserPassword);
        submitButton.click();
        return new SuccessfulPasswordResetPage(webDriver);
    }

    /**
     * @return
     * method that checks is SetNewPasswordPage loaded
     */
    public boolean isLoaded() {
        return newPasswordField.isDisplayed();
    }
}