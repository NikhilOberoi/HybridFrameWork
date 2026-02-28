package Test.TestRunner;

import org.Browser.ExtendedDriver;
import org.System.Reporter;
import org.Web.ObjectRepository;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static org.Browser.ExtendedDriver.driver;

public class TestRunner {

 // Load Environment Variables
    // initialize Rporter
    // initialize driver

    // We need to set attribute 'alwaysRun' as true as we are using groups in out test class
    // Otherwise BeforeSuite will not execute
    @BeforeSuite(alwaysRun = true)
    public static void initializeTest(){
        Reporter.InitializeReport();
        ObjectRepository.readAllLocators();
        ExtendedDriver.InitDriver();
        //Database
        //Initialize Driver here
    }

    // We need to set attribute 'alwaysRun' as true as we are using groups in out test class
    // Otherwise AfterSuite will not execute
    @AfterSuite(alwaysRun = true)
    public static void clearResources(){
        //driver.quit();
        Reporter.FinaliseReport();

        //Database Connections
    }
}