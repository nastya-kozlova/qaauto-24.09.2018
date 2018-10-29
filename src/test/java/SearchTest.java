import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {
    WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://linkedin.com");
        loginPage = new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    /**
     * PreConditions:
     * - Open new Browser.
     * - Navigate to linkedin.com.
     *
     * Scenario:
     * - Verify that login page is loaded.
     * - Login with valid credentials.
     * - Verify that Home page is loaded.
     * - Enter "serchTerm" into search field and press RETURN key.
     * - Verify that search page is loaded.
     * - Verify  10 serchResults displayed on page.
     * - Verify each result item contains "serchTerm".
     *
     *
     * PostConditions:
     * - Close browser.
     */

    @Test
    public void  basicSearchTest (){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        HomePage homePage = loginPage.login("nastya_kozlova@hotmail.com","1992mypas" );

        Assert.assertTrue(homePage.isPageLoaded(),
                "HomePage is not displayed on Login page.");


    }
}
