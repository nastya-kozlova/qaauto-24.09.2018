import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage {


    private WebDriver webDriver;

    @FindBy(xpath ="//span[@id='session_password-login-error']")
    private WebElement passwordMessage;

    @FindBy (xpath ="//span[@id='session_key-login-error']")
    private WebElement emailMessage;

    @FindBy (xpath ="//div[@class='alert error']")
    private WebElement generalErrorMessage;

    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    public boolean isPageLoaded (){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/login-submit")
                && webDriver.getTitle().equals("Войти в LinkedIn")
                && isGeneralErrorMessageDisplayed();
    }


    public boolean isPasswordMessageDisplayed () {
        return  passwordMessage.isDisplayed();
    }

    public boolean isEmailMessageDisplayed () {

        return  emailMessage.isDisplayed();
    }

    public boolean isGeneralErrorMessageDisplayed () {
        return generalErrorMessage.isDisplayed();
    }

    public String getTextOfEmailMessage ( ){
        return emailMessage.getText();
    }

    public String getTextOfPasswordMessage (){
        return passwordMessage.getText();
    }

    public String getAlertMessageText() {
        return generalErrorMessage.getText();
    }
}

