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
import com.applitools.eyes.*;
import com.applitools.eyes.selenium.*;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.*;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.ITestContext;
//import org.testng.ITestResult;
//import org.testng.annotations.*;


public class BaseTests {

  protected static WebDriver driver;
  protected static SearchPage page;
  protected static Eyes eyes;
  public String url = "https://katsaldivar.github.io/automated-visual-testing-test-site/";

  @BeforeClass
  public static void setUp() {
    Properties props = System.getProperties();
    try {
        props.load(new FileInputStream(new File("resources/test.properties")));
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
      driver.quit();
  }

    /*@AfterMethod //https://applitools.com/docs/topics/overview/overview-writing-tests-with-vg.html
    public void afterEachTest(ITestResult result) {
        // check if an exception was thrown
        boolean testPassed = result.getStatus() == ITestResult.FAILURE;
        if (testPassed) {
            // Close the Eyes instance, no need to wait for results, we'll get those at the end in afterTestSuite
            eyes.closeAsync();
        } else {
            // There was an exception so the test may be incomplete - abort the test
            eyes.abortIfNotClosed();
        }
        driver.quit();
    }

    @AfterSuite
    public void afterTestSuite(ITestContext testContext) {
        //Wait until the test results are available and retrieve them
        TestResultsSummary allTestResults = runner.getAllTestResults(false);
        for (TestResultContainer result : allTestResults) {
            handleTestResults(result);
        }
    }
    */

    void handleTestResults(TestResultContainer summary) {
        Throwable ex = summary.getException();
        if (ex != null ) {
            System.out.printf("System error occured while checking target.\n");
        }
        TestResults result = summary.getTestResults();
        if (result == null) {
            System.out.printf("No test results information available\n");
        } else {
            System.out.printf("URL = %s, AppName = %s, testname = %s, Browser = %s,OS = %s, viewport = %dx%d, matched = %d,mismatched = %d, missing = %d,aborted = %s\n",
                    result.getUrl(),
                    result.getAppName(),
                    result.getName(),
                    result.getHostApp(),
                    result.getHostOS(),
                    result.getHostDisplaySize().getWidth(),
                    result.getHostDisplaySize().getHeight(),
                    result.getMatches(),
                    result.getMismatches(),
                    result.getMissing(),
                    (result.isAborted() ? "aborted" : "no"));
        }
    }

  private static void initializeEyes() {
    eyes = new Eyes();
    //eyes = new Eyes(new VisualGridRunner(20));
    eyes.setApiKey(System.getProperty("applitools.api.key"));
    //eyes.setConfiguration(VisualGridConfig.getGrid("Automation Bookstore", "testSearchByFullTitle"));
  }

  protected void validateWindow() {
    eyes.open(driver,
            "Automation Bookstore",
            Thread.currentThread().getStackTrace()[2].getMethodName());
    driver.get(url);
    eyes.setForceFullPageScreenshot(true);//scrolls vertically and horizontally to get the whole visible page
    eyes.checkWindow();
    eyes.close();
  }

    protected void validateElement(By locator) {
        eyes.open(driver,
                "Automation Bookstore",
                Thread.currentThread().getStackTrace()[2].getMethodName());
        driver.get(url);
        eyes.checkElement(locator);
        eyes.close();
    }
}
