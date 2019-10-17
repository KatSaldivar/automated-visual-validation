package tests;

import static com.applitools.eyes.selenium.fluent.Target.*;
import static org.junit.Assert.*;

import com.applitools.eyes.MatchLevel;
import data.BookData;
import models.Book;
import org.junit.Test;
import base.BaseTests;
import org.openqa.selenium.By;

import java.lang.annotation.Target;

//https://www.pnsqc.org/w02-introduction-to-automated-visual-validation/
//file:///Users/katherinesaldivar/Documents/GitHub/automated-visual-testing/project/website/index.html

public class SearchTests extends BaseTests {

  @Test
  public void testSearchByFullTitle(){
    //Book book = new Book("Agile Testing","Lisa Crispin and Janet Gregory","$49.07","file:///Users/katherinesaldivar/Documents/GitHub/automated-visual-testing/project/website/img/crispin.jpg");
    String title = "Agile Testing";
    page.search(title);
    eyes.setMatchLevel(MatchLevel.CONTENT);//test content, not colors/styling
    //eyes.check(region(BookData.AGILE_TESTING.getLocator()).ignore(By.id("pid3_price")));
    //assertTrue(title + " is not visible", page.isBookVisible(book));
    assertEquals("Incorrect number of visible books",1, page.getNumberOfVisibleBooks());
    //validateWindow();
    validateElement(BookData.AGILE_TESTING.getLocator());
  }

    @Test
    public void testColor(){ //change price color and not fail test
        String title = "Agile Testing";
        page.search(title);
        eyes.setMatchLevel(MatchLevel.CONTENT);//test content, not colors/styling
        assertEquals("Incorrect number of visible books",1, page.getNumberOfVisibleBooks());
        validateElement(BookData.AGILE_TESTING.getLocator());
    }

    @Test
    public void testDynamicPage(){
        eyes.setMatchLevel((MatchLevel.LAYOUT)); //tests the layout pattern, even though the content might be dynamic
        validateWindow();
    }

}
