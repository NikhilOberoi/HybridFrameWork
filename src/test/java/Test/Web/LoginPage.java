package Test.Web;

import Test.TestRunner.TestRunner;
import org.System.Reporter;
import org.Web.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class LoginPage extends TestRunner {
    public static Logger log = LogManager.getLogger(LoginPage.class);
    @Test(testName = "Click Language Button", description = "Validated Language Buttion Functionality", groups = {"Smoke"})
    public static void SmokeTest()  {

        Element.elementToBeClickable("homepage.languagedropdown").click();
        Element.elementToBeClickable("homepage.languagecancel").click();
        Reporter.ReportPass("Validate Cancel Button on Language Page", "CancelButton Appeared successfully", true);

        Reporter.ReportPass("Validate Language Button", "Language button is clicked successfully", true);
    }
    @Test(testName = "Regression Test", description = "Validated Language Cancel Button", groups = {"Regression"})
    public static void Regression()  {
        Element.elementToBeClickable("homepage.searchbox").sendKeys("Washing Machine");
        Element.elementToBeClickable("homepage.searchbutton").sendKeys("Washing Machine");

}
}
