package org.Browser;
import org.System.EnvironmentInfo;
import org.Web.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ExtendedDriver {
    public static Logger log = LogManager.getLogger(ExtendedDriver.class);
    static BrowserType Browser = EnvironmentInfo.getBrowserType();
    public static String CurrentUrl = EnvironmentInfo.getWebURL();
    public static WebDriver driver = null;
    public static WebDriverWait Wait = null;

    // Initialize the given driver
    public static void InitDriver() {
        // PurgeOld Session Data
        log.info("Initializing Browser: "+ Browser.toString());
        switch (Browser) {
            case Chrome:
                driver = ChromeInit(getChromeOptions());
                break;
            case ChromeHeadless:
                driver = ChromeInit(getChromeHeadlessOptions());
                break;
            case Edge:
                driver = EdgeInit(getEdgeOptions());
                break;
            case EdgeHeadless:
                driver = EdgeInit(getEdgeHeadlessOptions());
                break;
            default:
                //Stop Execution;
                break;
        }
        if(driver != null)
            DriverWait();
        log.info("Browser Initialized Successfully: "+ Browser.toString());
    }

    // Setup Browser Options:
    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-certificate-errors");
        return options;
    }

    private static ChromeOptions getChromeHeadlessOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--headless");
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-certificate-errors");
        return options;
    }

    private static EdgeOptions getEdgeHeadlessOptions() {
        EdgeOptions options = new EdgeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--headless");
        return options;
    }

    // Initialize Drivers based on browser type
    private static WebDriver ChromeInit(ChromeOptions options) {

        WebDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.get(CurrentUrl);
        return chromeDriver;
    }
    private static WebDriver EdgeInit(EdgeOptions options) {

        WebDriver edgeDriver = new EdgeDriver(options);
        edgeDriver.get(CurrentUrl);
        return edgeDriver;
    }

    private static void  DriverWait(){
        log.info("Initializing explicit wait.");
        Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}