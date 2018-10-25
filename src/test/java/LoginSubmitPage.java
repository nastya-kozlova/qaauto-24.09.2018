import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage {

    private WebDriver webDriver;

    @FindBy(xpath ="//div[@id='error-for-password']")
    private WebElement passwordMessage;
    @FindBy (xpath ="//div[@id='error-for-username']")
    private WebElement emailMessage;

    public LoginSubmitPage (WebDriver webDriver) {
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



}
