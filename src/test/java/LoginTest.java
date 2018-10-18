import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver webDriver;//лбъявили переменную
    @BeforeMethod
    public void beforeMethod () {
        webDriver = new FirefoxDriver();//проинициализировали переменную

    }
    @AfterMethod
    public void afterMethod () {
        webDriver.quit();
    }

    /**
     * PreConditions:
     * - Open FF browser.
     *
     * Scenario:
     * - Navigate to https://linkedin.com.
     * - Verify that Login page is loaded.
     * - Enter userEmail into userEmail field.
     * - Enter userPassword into userPassword field.
     * - Click on signIn button.
     * - Verify that Home page is loaded.
     *
     * PostCondition:
     * - Close FF browser.
     */
    @Test
    public void successfulLoginTest() {

        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Home page URL is wrong.");
        loginPage.login( "nastya_kozlova@hotmail.com", "1992mypas");


        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Homepage URL is wrong");

    }
    @Test
    public void  negativeLoginTest () {
        webDriver.get("https://linkedin.com");
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Home page URL is wrong.");

        WebElement logInEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement passwordDataField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));

        logInEmailField.sendKeys("a@b.c");
        passwordDataField.sendKeys("");
        signInButton.click();

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Homepage URL is wrong");


    }





}




