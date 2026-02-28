package Test.Web;

import Test.TestRunner.TestRunner;
import org.Browser.ExtendedDriver;
import org.System.Reporter;
import org.Web.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;

import static org.Browser.ExtendedDriver.driver;

public class LoginPage extends TestRunner {
    public static Logger log = LogManager.getLogger(LoginPage.class);
    @Test(testName = "Click Language Button", description = "Validated Language Buttion Functionality", groups = {"Smoke", "Sanity"})
    public static void Login()  {

        Element.elementToBeClickable("homepage.languagedropdown").click();
        //Element.elementToBeClickable("homepage.languagecancel").click();

        Reporter.ReportPass("Validate Language Button", "Language button is clicked successfully", true);
       //Reporter.ReportFail("Test Login_1 ", "Test is Failed", true);
    }
    @Test(testName = "Cancle Language Button", description = "Validated Language Cancel Button", groups = {"Smoke", "Sanity"})
    public static void Login2()  {
        Element.elementToBeClickable("homepage.languagecancel").click();
        Reporter.ReportPass("Validate Cancel Button on Language Page", "CancelButton Appeared successfully", true);
    }
    public static void main(String[] args){
        initializeTest();
        Login();
        clearResources();
    }
}
