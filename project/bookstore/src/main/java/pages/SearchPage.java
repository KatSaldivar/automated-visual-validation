package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import models.Book;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class SearchPage {

  private WebDriver driver;

  private By searchBar = By.id("searchBar");

  private By visibleBooks = By.cssSelector("li:not(.ui-screen-hidden)");

  private By hiddenBooks = By.cssSelector("li.ui-screen-hidden");

  private By titleAttribute = By.xpath(".//h2[contains(@id, '_title')]");

  private By authorAttribute = By.xpath(".//p[contains(@id, '_author')]");

  private By priceAttribute = By.xpath(".//p[contains(@id, '_price')]");

  private By imageAttribute = By.xpath(".//img[contains(@id, '_thumb')]");

  public SearchPage(WebDriver driver){
    this.driver = driver;
  }

  public void search(String text) {
    search(text, true);
  }

  public void search(String text, boolean waitForHidden) {
    clearSearch();

    driver.findElement(searchBar).sendKeys(text);

    if(waitForHidden) {
      WebDriverWait wait = new WebDriverWait(driver, 5);
      wait.until(presenceOfElementLocated(hiddenBooks));
    }
  }

  public void clearSearch() {
    driver.findElement(searchBar).clear();

    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(numberOfElementsToBe(hiddenBooks, 0));
  }

  public int getNumberOfVisibleBooks() {
    return findVisibleBooks().size();
  }

  public boolean isBookVisible(String title){
    List<WebElement> books = findVisibleBooks();

    for(WebElement book : books) {
      if(title.equalsIgnoreCase(book.findElement(titleAttribute).getText())){
        return true;
      }
    }

    return false;
  }

  public boolean isBookVisible(Book book){
    List<WebElement> books = findVisibleBooks();

    for(WebElement b: books) {
      if(book.getTitle().equalsIgnoreCase(
          b.findElement(titleAttribute).getText())
        && book.getAuthor().equalsIgnoreCase(
            b.findElement(authorAttribute).getText())
        && book.getPrice().equals(
            b.findElement(priceAttribute).getText())
        && b.findElement(imageAttribute).getAttribute("src")
            .endsWith(book.getImage())
          ){
        return true;
      }
    }

    return false;
  }

  private List<WebElement> findVisibleBooks(){
    return driver.findElements(visibleBooks);
  }
}
