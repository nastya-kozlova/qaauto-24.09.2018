package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static java.lang.Thread.sleep;


public class LoginPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement logInEmailField;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordDataField;

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(className= "link-forgot-password")
    private WebElement forgotPasswordLink;

    /**
     * @param webDriver
     * method with initialization of webDriver and webElements on the page
     */
    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return
     * method which click on ForgotPassword button
     */
    public RequestPasswordResetPage clickOnForgotPasswordLink() {
        waitUntilElementIsClickable(forgotPasswordLink);
        forgotPasswordLink.click();
        return new RequestPasswordResetPage(webDriver);
    }

    /**
     * @return
     * method that checks is LoginPage loaded
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")
                && isSignInButtonDisplayed();
    }


    /**
     * @return
     * method that checks is signInButton displayed
     */
    public boolean isSignInButtonDisplayed() {

        return signInButton.isDisplayed();
    }

    /**
     * Method that logs in with specific credentials.
     * @param userEmail - String with userEmail.
     * @param userPassword - String with userPassword.
     * @param <T> - Generic type to cast differentPage Objects.
     * @return Either HomePage/LoginSubmitPage/LoginPage.
     * method entering email, password and clicking on signInButton for logining into linkedin.com
     */
    public <T> T login(String userEmail, String userPassword) {
        logInEmailField.sendKeys(userEmail);
        passwordDataField.sendKeys(userPassword);
        signInButton.click();
        try {
            sleep (3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (webDriver.getCurrentUrl().contains("/feed")) {
            return (T) new HomePage(webDriver);
        }
        if (webDriver.getCurrentUrl().contains("uas/login-submit")){
            return (T) new LoginSubmitPage(webDriver);
        } else {
            return (T) new LoginPage(webDriver);
        }
    }
    
    
}
