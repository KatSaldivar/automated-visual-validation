package base;

import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.model.TestResultContainer;
import com.applitools.eyes.visualgrid.model.TestResultSummary;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import pages.SearchPage;


public class BaseTests {

  protected static WebDriver driver;
  protected static SearchPage page;
  protected static Eyes eyes;

  @BeforeClass
  public static void setUp() {
    Properties props = System.getProperties();
    try {
      props.load(
              new FileInputStream(new File("resources/test.properties")));
    } catch(Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }

    driver = new ChromeDriver();
    driver.get(System.getProperty("app.url"));
    page = new SearchPage(driver);
    initializeEyes();

  }

  @AfterClass
  public static void tearDown() {
      try {
          // all checks done - wrap up test
          eyes.closeAsync();
          // all tests done - wrap up test suite
          TestResultSummary allTestResults = runner.getAllTestResults(throwExceptionOnFailure);
          TestResultContainer[] results = allTestResults.getAllResults();
      } finally {
          driver.quit();
      }
  }

  private static void initializeEyes() {
    VisualGridRunner runner = new VisualGridRunner(20);
    eyes = new Eyes();
    Eyes eyes = new Eyes(runner);
    eyes.setApiKey(System.getProperty("applitools.api.key"));
    eyes.setConfiguration(VisualGridConfig.getGrid("Bookstore", "Grid-Test"));
  }

  protected void validateWindow() {
    eyes.open(driver,
            "Automation Bookstore",
            Thread.currentThread().getStackTrace()[2].getMethodName());
    eyes.setForceFullPageScreenshot(true);//scrolls vertically and horizontally to get the whole visible page
    eyes.checkWindow();
    eyes.close();
  }

    protected void validateElement(By locator) {
        eyes.open(driver,
                "Automation Bookstore",
                Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkElement(locator);
        eyes.close();
    }
}
