package Test.TestRunner;

import org.System.Reporter;
import org.Web.ObjectRepository;

import static org.Browser.ExtendedDriver.driver;

public class TestRunner {

 // Load Environment Variables
    // Load Object Repository.properties
    // initialize Rporter
    // initialize driver
//Reporter.InitializeReport();

    public static void initializeTest(){
        Reporter.InitializeReport();
        ObjectRepository.readAllLocators();
        //Database

    }

    public static void clearResources(){
        Reporter.FinaliseReport();
        driver.quit();
        //Database Connections

    }

}
