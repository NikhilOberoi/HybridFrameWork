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
    @Test(groups = {"Smoke", "Sanity"})
    public static void Login() throws InterruptedException {
        //chromeDriver.findElement(By.xpath("//a[@id='nav-logo-sprites']"));
        Element.elementToBeClickable("homepage.languagedropdown").click();
        //Element.getWebElement("homepage.languagedropdown").click();
        Element.elementToBeClickable("homepage.languagecancel").click();

        Reporter.CreateTestScenario("1212", "Login Scenario");
        Reporter.CreateTestCase("Test Case-1222", "Login Test Case");
        Reporter.ReportPass("Test Login ", "Test is passed", true);
        Reporter.ReportPass("Test Login1 ", "Test is passed", true);
        Reporter.ReportFail("Test Login2 ", "Test is Failed", true);
    }
    public static void main(String[] args) throws InterruptedException {
        initializeTest();
        Login();
        clearResources();
    }
}
