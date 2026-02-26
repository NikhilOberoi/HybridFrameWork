package Test.Web;

import Test.TestRunner.TestRunner;
import com.aventstack.extentreports.model.Report;
import org.Browser.ExtendedDriver;
import org.System.Reporter;
import org.Web.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.Web.ObjectRepository;

import static org.Web.ObjectRepository.locators;

public class LoginPage {

    static WebDriver driver = ExtendedDriver.driver;

    public static void main(String[] args) throws InterruptedException {

        TestRunner.initializeTest();
        Element.getWebElement("homepage.languagedropdown").click();
       //Thread.sleep(2000);

       Reporter.CreateTestScenario("1212","Login Scenario");
       Reporter.CreateTestCase("Test Case-1222", "Login Test Case");
       Reporter.ReportPass("Test Login ","Test is passed",true);
        Reporter.ReportPass("Test Login1 ","Test is passed",true);
        Reporter.ReportFail("Test Login2 ","Test is Failed",true);

        TestRunner.clearResources();
    }
}
