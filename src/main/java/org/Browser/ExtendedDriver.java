package org.Browser;
import org.System.EnvironmentInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


public class ExtendedDriver {
    public static Logger log = LogManager.getLogger(ExtendedDriver.class);
    static BrowserType Browser = EnvironmentInfo.getBrowserType();
    public static String CurrentUrl = EnvironmentInfo.getWebURL();
    public static WebDriver driver = null;

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
            case Firefox:
                driver = FirefoxInit(getFirefoxOptions());
                break;
            case FirefoxHeadless:
                driver = FirefoxInit(getFirefoxHeadlessOptions());
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
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notification");
        options.addArguments("--incognito");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-infobars");
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setScriptTimeout(Duration.ofSeconds(5));
        //options.setScriptTimeout(Duration.of(5,ChronoUnit.SECONDS));
        options.setPageLoadTimeout(Duration.ofSeconds(15));
        options.setImplicitWaitTimeout(Duration.ofSeconds(5));
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
        log.info("Running Test Suite on Browser: "+ options.getBrowserName());
        return options;
    }

    private static ChromeOptions getChromeHeadlessOptions() {
        ChromeOptions options = getChromeOptions();
        options.addArguments("--headless = new");
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.setAcceptInsecureCerts(true);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-certificate-errors");
        return options;
    }

    private static EdgeOptions getEdgeHeadlessOptions() {
        EdgeOptions options = getEdgeOptions();
        options.addArguments("--headless = new");
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.setAcceptInsecureCerts(true);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-certificate-errors");
        return options;
    }

    private static FirefoxOptions getFirefoxHeadlessOptions() {
        FirefoxOptions options = getFirefoxOptions();
        options.addArguments("--headless = new");
        return options;
    }

    // Initialize Chrome Driver
    private static WebDriver ChromeInit(ChromeOptions options) {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.get(CurrentUrl);
        return chromeDriver;
    }
    // Initialize Edge Driver
    private static WebDriver EdgeInit(EdgeOptions options) {
        //System.setProperty("webdriver.edge.driver", "path/to/your/msedgedriver.exe");
        WebDriver edgeDriver = new EdgeDriver(options);
        edgeDriver.get(CurrentUrl);
        return edgeDriver;
    }

    // Initialize Firefox Driver
    private static WebDriver FirefoxInit(FirefoxOptions options) {
       // System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");
        WebDriver firefoxDriver = new FirefoxDriver(options);
        firefoxDriver.get(CurrentUrl);
        return firefoxDriver;
    }
}