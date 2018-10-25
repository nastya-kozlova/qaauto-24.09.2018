import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;


public class LoginPage {

    private WebDriver webDriver;//объявили

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement logInEmailField;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordDataField;

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    public LoginPage(WebDriver webDriver) { //конструктор. работает после инициализации переменных
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")
                && isSignInButtonDisplayed();
    }


    public boolean isSignInButtonDisplayed() {

        return signInButton.isDisplayed();
    }

    public <T> T login(String userEmail, String userPassword) {//один метод вместо трех
        logInEmailField.sendKeys(userEmail);
        passwordDataField.sendKeys(userPassword);
        signInButton.click();
        try {
            sleep (3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (webDriver.getCurrentUrl().contains("/feed")) {
            return (T) new HomePage (webDriver);
        }
        if (webDriver.getCurrentUrl().contains("uas/login-submit")){
            return (T) new LoginSubmitPage(webDriver);
        } else {
            return (T) new LoginPage(webDriver);
        }
    }
}
