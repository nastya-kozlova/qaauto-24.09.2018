import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver webDriver;//объявили переменную

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();//проинициализировали переменную

    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    /**
     * PreConditions:
     * - Open FF browser.
     * <p>
     * Scenario:
     * - Navigate to https://linkedin.com.
     * - Verify that Login page is loaded.
     * - Enter userEmail into userEmail field.
     * - Enter userPassword into userPassword field.
     * - Click on signIn button.
     * - Verify that Home page is loaded.
     * <p>
     * PostCondition:
     * - Close FF browser.
     */

    @Test
    public void successfulLoginTest() {

        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

       Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        loginPage.login("nastya_kozlova@hotmail.com", "1992mypas");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Homepage URL is wrong");
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn", "Home page title is wrong.");
        //li[@id='profile-nav-item']

        HomePage homePage = new HomePage(webDriver);

       // Assert.assertTrue(homePage.profileNavItem.isDisplayed(), "profileNavItem is not displayed on Login Page");


    }

    @Test
    public void negativeLoginWithEmptyPasswordTest() {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Home page URL is wrong.");
        loginPage.login("", "");//вызов метода логин, который есть в классе логинпейдж.джава


        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Homepage URL is wrong");

    }

    @Test
    public void wrongPasswordTest() {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Home page URL is wrong.");
        loginPage.login("nastya_kozlova@hotmail.com", "658hsgdsd");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME", "Homepage URL is wrong");

    }


}




