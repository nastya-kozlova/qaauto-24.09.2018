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


    public boolean isPageLoadedWithPasswordMessage (){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/login-submit")
                && webDriver.getTitle().equals("Войти в LinkedIn")
                && isPasswordMessageDisplayed();
    }

    public boolean isPageLoadedWithEmailMessage (){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/login-submit")
                && webDriver.getTitle().equals("Войти в LinkedIn")
                && isEmailMessageDisplayed ();
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

    public String textOfEmailMessage ( ){
        return emailMessage.getText();
    }

    public String textOfPasswordMessage (){
        return passwordMessage.getText();
    }
}
