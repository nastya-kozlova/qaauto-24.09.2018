import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver webDriver;//объявили
    WebElement logInEmailField ;
    WebElement passwordDataField;
    WebElement signInButton ;

    public LoginPage(WebDriver webDriver) { //конструктор. работает после инициализации переменных
        this.webDriver = webDriver;
        initElements();
    }

public void initElements(){
    logInEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
    passwordDataField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
    signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));


}
public void login(String userEmail, String userPassword){
        logInEmailField.sendKeys(userEmail);
        passwordDataField.sendKeys(userPassword);
        signInButton.click();

    }




}
