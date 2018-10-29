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
    public Object[][] leadingToLoginSubmitDataProvider() {
        return new Object[][]{
                { "a", "1992mypas", "Слишком короткий текст (минимальная длина – 3 симв., введено – 1 симв.).", "" },
                {"TeSt", "1992mypas", "Укажите действительный адрес эл. почты.", ""},
                {"test", "1", "","Пароль должен содержать не менее 6 символов."},
                {"nastya_kozlova@hotmail.com","wrongwrong" , "",  "Это неверный пароль. Повторите попытку или "}
        };
    }

    @DataProvider
    public Object[][] stayingOnTheLoginPageDataProvider() {
        return new Object[][]{
                {"","1992mypas"},
                {"nastya_kozlova@hotmail.com",""},
                {"",""}
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

//дз к 8 занятию

    @Test (dataProvider = "leadingToLoginSubmitDataProvider")
    public void negativeLeadingToLoginSubmitPage (String userEmail, String userPassword, String emailMessage, String
                                                  passwordMessage){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginSubmitPage.isGeneralErrorMessageDisplayed());
        Assert.assertEquals(loginSubmitPage.textOfEmailMessage(), emailMessage, "Different result");
        Assert.assertEquals(loginSubmitPage.textOfPasswordMessage(), passwordMessage, "Different result");
    }

    @Test (dataProvider = "stayingOnTheLoginPageDataProvider")
    public void negativeStayingOnLoginPage (String userEmail, String userPassword){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }

    //дз к 7 занятию

    @Test
    public void wrongEmailTest() {
        webDriver.get("https://linkedin.com");

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        LoginSubmitPage loginSubmitPage = loginPage.login("a@b", "1992mypas");


        Assert.assertTrue(loginSubmitPage.isEmailMessageDisplayed(), "Login Submit page is not loaded");

    }

    @Test
    public void wrongPasswordTest() {
        webDriver.get("https://linkedin.com");

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        LoginSubmitPage loginSubmitPage = loginPage.login("nastya_kozlova@hotmail.com", "658hsgdsd");

        Assert.assertTrue(loginSubmitPage.isPasswordMessageDisplayed(), "Login Submit page is not loaded");

    }

    //негативные, при которых мы остаемся на той же странице

    @Test
    public void emptyEmailTest () {
        webDriver.get ("https://linkedin.com/");

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        loginPage.login("", "1992mypas");

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }

    @Test
    public void negativeLoginWithEmptyPasswordTest() {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        loginPage.login("nastya_kozlova@hotmail.com", "");


        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

    }


}




