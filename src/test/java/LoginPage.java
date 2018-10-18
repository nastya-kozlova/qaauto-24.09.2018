import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {

    private WebDriver webDriver;//объявили

    private WebElement logInEmailField;
    private WebElement passwordDataField;
    private WebElement signInButton;

    public LoginPage(WebDriver webDriver) { //конструктор. работает после инициализации переменных
        this.webDriver = webDriver;
        initElements();
    }

    public boolean isPageLoaded() {
      /*  Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Home page URL is wrong.");
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться", "Login page title is wrong.");

        Assert.assertTrue(isSignInButtonDisplayed(), "SignIn Button is not displayed on Login Page");*/
//вместо ассертов делаем ретурны
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")
                && isSignInButtonDisplayed();
    }


    public boolean isSignInButtonDisplayed() {
        return signInButton.isDisplayed();
    }


    private void initElements() {
        logInEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        passwordDataField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));


    }

    public void login(String userEmail, String userPassword) {
        logInEmailField.sendKeys(userEmail);
        passwordDataField.sendKeys(userPassword);
        signInButton.click();

    }


}
