package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {
   private WebDriver webDriver;

   @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;

    @FindBy(xpath = "//*[@type='search-icon']")
    private WebElement searchIcon;

    @FindBy(xpath = "//input[contains(@aria-owns, 'results')]")
    private WebElement searchField;


    /**
     * @param webDriver
     * method with initialization of webriver and webelements on the page
     */
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return
     * method that checks is HomePage loaded
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && webDriver.getTitle().contains("LinkedIn")
                && profileNavItem.isDisplayed();
    }

    /**
     * @param searchTerm
     * @return
     * method which enter keys in search field and click search field
     */
    public SearchPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchIcon.sendKeys(Keys.RETURN);
        waitUntilElementIsVisible(profileNavItem, 5);//тут должен быть вейт пока появится список элементов, для него нужен вебэлемент
        return new SearchPage(webDriver);
    }

}

