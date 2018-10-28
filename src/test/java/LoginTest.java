import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "nastya_kozlova@hotmail.com", "1992mypas" },
                { "nastya_Kozlova@hotmail.com", "1992mypas"},
                { "  nastya_kozlova@hotmail.com ", "1992mypas" }
        };
    }

    @DataProvider
    public Object[][] leadingToLoginSubmitTestDataProvider() {
        return new Object[][]{
                { "a", "1992mypas", "Укажите действительный адрес эл. почты.", "" },
        };
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

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {

        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

       Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        HomePage homePage = loginPage.login(userEmail, userPassword);

        Assert.assertTrue(homePage.isPageLoaded(),
                "HomePage is not displayed on Login page.");
    }

    @Test
    public void negativeLoginWithEmptyPasswordTest() {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        loginPage.login("nastya_kozlova@hotmail.com", "");


        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

    }

    @Test
    public void wrongPasswordTest() {//LoginSubmit
        webDriver.get("https://linkedin.com");

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        LoginSubmitPage loginSubmitPage = loginPage.login("nastya_kozlova@hotmail.com", "658hsgdsd");

        Assert.assertTrue(loginSubmitPage.isPasswordMessageDisplayed(), "Login Submit page is not loaded");

    }


    @Test
    public void emptyEmailTest () {
        webDriver.get ("https://linkedin.com/");

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        loginPage.login("", "1992mypas");

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }

    @Test
    public void wrongEmailTest() {
        webDriver.get("https://linkedin.com");

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        LoginSubmitPage loginSubmitPage = loginPage.login("a@b", "1992mypas");


        Assert.assertTrue(loginSubmitPage.isEmailMessageDisplayed(), "Login Submit page is not loaded");

    }


}




