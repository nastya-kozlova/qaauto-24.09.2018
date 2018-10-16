import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

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
      /*
        //стринги с исходными данными
        String logInEmail = "nastya_kozlova@hotmail.com";
        String passwordData = "test123";
        String webIdLogIn = "login-email";
        String webIdPassword = "login-password";
        String signInButtonId = "login-submit";

        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://linkedin.com");
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Home page URL is wrong.");

        //тестовый сценарий
        webDriver.findElement(By.id(webIdLogIn)).sendKeys(logInEmail);
        webDriver.findElement(By.id(webIdPassword)).sendKeys(webIdPassword);
        webDriver.findElement(By.id(signInButtonId)).click();
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/", "URL is wrong: log in is not successful");
        webDriver.quit();
        */

        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://linkedin.com");
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Home page URL is wrong.");

        WebElement logInEmailField = webDriver.findElement(By.xpath("input[@id='login-email']"));
        WebElement passwordDataField = webDriver.findElement(By.xpath("input[@]id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("input[@id='login-submit']"));

        String logInEmail = "nastya_kozlova@hotmail.com";
        String passwordData = "test123";

        logInEmailField.sendKeys(logInEmail);
        passwordDataField.sendKeys(passwordData);
        signInButton.click();

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Homepage URL is wrong");

        webDriver.quit();

    }
}
