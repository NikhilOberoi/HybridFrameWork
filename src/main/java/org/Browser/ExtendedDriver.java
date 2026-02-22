package org.Browser;
import org.System.EnvironmentInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


public class ExtendedDriver {

    static BrowserType Browser = EnvironmentInfo.getBrowserType();
    public static String CurrentUrl = EnvironmentInfo.getWebURL();
    public static WebDriver driver = InitDriver(Browser);

    // Initialize the given driver
    private static WebDriver InitDriver(BrowserType browser) {
        // PurgeOld Session Data

        WebDriver driver = null;
        switch (browser) {
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
        }
        return driver;
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
}