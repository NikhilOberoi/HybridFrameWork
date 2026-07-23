package Test.Web;

import Test.TestRunner.TestRunner;
import org.System.Reporter;
import org.Web.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class LoginPage extends TestRunner {
    public static Logger log = LogManager.getLogger(LoginPage.class);

    @Test(testName = "Sanity", description = "Validated Language Buttion Functionality", groups = {"Sanity"})
    public static void SmokeTest() {
        log.info("Running Smoke Suite Test Cases.");
        Element.ExpectedCondition("homepage.languagedropdown","elementToBeClickable").click();
        Element.ExpectedCondition("homepage.languagecancel","elementToBeClickable").click();
        Reporter.ReportPass("Validate Cancel Button on Language Page", "CancelButton Appeared successfully", true);

        Reporter.ReportPass("Validate Language Button", "Language button is clicked successfully", true);
        log.info("Smoke Suite Test Cases execution completed.");
    }

    @Test(testName = "Regression Test", description = "Validated Language Cancel Button", groups = {"Sanity"})
    public static void Regression() {
        log.info("Running Regression Suite Test Cases.");
        Element.ExpectedCondition("homepage.searchbox","elementToBeClickable").sendKeys("Washing Machine");
        Element.ExpectedCondition("homepage.searchbutton","elementToBeClickable").sendKeys("Washing Machine");
        log.info("Regression Suite Test Cases execution completed.");
         Reporter.ReportPass("Validate Search Box", "Search successfully", true);
    }
}
