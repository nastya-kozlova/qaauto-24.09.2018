package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> searchResultList;


    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchBar;

    /**
     * @param webDriver
     * method with initialization of webDriver and webElements on the page
     */
    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    /**
     * @return
     * method that checks is SearchPage loaded
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("/search/results/")
                && webDriver.getTitle().contains("| Поиск | LinkedIn")
                && searchBar.isDisplayed();
    }

    public int getSearchResultsCount() {
        return searchResultList.size();
    }

    /**
     * @return
     * method that get us search results in list
     */
    public List<String> getSearchResults() {
        List <String> searchResultStringList = new ArrayList<String>();
        for (WebElement searchResult: searchResultList) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", searchResult);
            String searchResultText = searchResult. getText();
            searchResultStringList.add(searchResultText);
        }
        return searchResultStringList;

    }


}
