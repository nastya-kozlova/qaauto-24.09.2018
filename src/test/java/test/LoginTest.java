package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import page.HomePage;
import page.LoginPage;
import page.LoginSubmitPage;



public class LoginTest {
    WebDriver webDriver;//объявили переменную
    LoginPage loginPage;//объявили переменную

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://linkedin.com");
        loginPage = new LoginPage(webDriver);//проинициализировали
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

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");

        HomePage homePage = loginPage.login(userEmail, userPassword);

        Assert.assertTrue(homePage.isPageLoaded(),
                "page.HomePage is not displayed on Login page.");
    }

//дз к 8 занятию


    @DataProvider
    public Object[][] validationMessagesCombination() {
        return new Object[][]{
                { "a", "1992mypas", "Слишком короткий текст (минимальная длина – 3 симв., введено – 1 симв.).", "" },
                {"TeSt", "1992mypas", "Укажите действительный адрес эл. почты.", ""},
                {"test", "1", "","Пароль должен содержать не менее 6 символов."},
                {"nastya_kozlova@hotmail.com","wrongwrong" , "",  "Это неверный пароль. Повторите попытку или "}
        };
    }
    @Test (dataProvider = "validationMessagesCombination")
    public void errorMessagesOnInvalidEmailPasswordTest (String userEmail, String userPassword, String emailMessage, String
                                                  passwordMessage){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);
       // Assert.assertTrue(loginSubmitPage.isPageLoaded);//-это нужно доделать (метод isPageLoaded)
        Assert.assertEquals (loginSubmitPage.getAlertMessageText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "");
        Assert.assertEquals(loginSubmitPage.getTextOfEmailMessage(), emailMessage, "Email validation message is wrong.");
        Assert.assertEquals(loginSubmitPage.getTextOfPasswordMessage(), passwordMessage, " Password validation message is wrong.");
    }

    @Test (dataProvider = "stayingOnTheLoginPageDataProvider")
    public void negativeStayingOnLoginPage (String userEmail, String userPassword){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }



}




