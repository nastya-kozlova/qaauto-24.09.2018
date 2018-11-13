package page;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class PasswordResetSubmitPage extends BasePage {
    private WebDriver webDriver;

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    /**
     * @param webDriver
     * method with initialization of webDriver and webElements on the page
     */
    public PasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return
     * method that checks is LoginSubmitPage loaded
     */
    public boolean isPageLoaded() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resendLinkButton.isDisplayed();
    }

    /**
     * @return
     * method which get link from Linkedin message
     */
    public SetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "as.arsenicum@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);
        String resetPasswordLink = StringUtils.substringBetween(
                message, "click <a href=\"", "\"").replace("amp;", "");
        System.out.println(resetPasswordLink);
        webDriver.get(resetPasswordLink);

        return new SetNewPasswordPage(webDriver);
    }
}