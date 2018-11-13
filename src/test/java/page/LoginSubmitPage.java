package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage {


    private WebDriver webDriver;

    @FindBy(xpath ="//span[@id='session_password-login-error']")
    private WebElement passwordMessage;

    @FindBy (xpath ="//span[@id='session_key-login-error']")
    private WebElement emailMessage;

    @FindBy (xpath ="//div[@class='alert error']")
    private WebElement generalErrorMessage;

    /**
     * @param webDriver
     * initialization of webriver and webelements on the page
     */
    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    /**
     * @return
     * method that checks is LoginSubmitPage loaded
     */
    public boolean isPageLoaded (){
        return webDriver.getCurrentUrl().contains("/uas/login-submit")
                && webDriver.getTitle().equals("Войти в LinkedIn")
                && isGeneralErrorMessageDisplayed();
    }



    /**
     * @return
     * method that checks is general message displayed
     */
    public boolean isGeneralErrorMessageDisplayed () {
        return generalErrorMessage.isDisplayed();
    }

    public String getTextOfEmailMessage ( ){
        return emailMessage.getText();
    }

    public String getTextOfPasswordMessage (){
        return passwordMessage.getText();
    }

    public String getAlertMessageText() {
        return generalErrorMessage.getText();
    }
}

