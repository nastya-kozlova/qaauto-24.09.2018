import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.security.Key;
import java.util.List;

import static java.lang.Thread.sleep;

public class BadCodeExample {

    public static void main(String args[]) throws InterruptedException {
        String searchTerm = "Selenium";
        System.out.println("Hello world!!");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.google.com/");
        WebElement searchfield = webDriver.findElement(By.xpath("//*[@id=\'lst-ib\']"));
        searchfield.sendKeys("Selenium");
        searchfield.sendKeys(Keys.ENTER);
        sleep(3000);
        List<WebElement> searchResults = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println("Search results count: " + searchResults.size());
        for (WebElement searchresult : searchResults) {
            String searchResultText = searchresult.getText();
            System.out.println(searchResultText);
            if (searchResultText.toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println("searchTerm" + searchTerm + " was found.");
            } else {
                System.out.println("searchTerm" + searchTerm + "was NOT found.");
            }

        }
    }
}






