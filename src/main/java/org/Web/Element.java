package org.Web;

import org.System.Reporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.Browser.ExtendedDriver.driver;
import static org.Web.ObjectRepository.locators;

public class Element {

    public static Logger log = LogManager.getLogger(Element.class);

    // Get Element against provided locator name
    public static WebElement getWebElement(String locator)  {
        String locatorValue = locators.getProperty(locator);
        log.info("Element Name:" + locator+ " is fetched from locators repository having value: " + locatorValue);
        return getLocator(locatorValue);
    }

    // Get Elements against provided locator name
    public static List<WebElement> getWebElements(String locator) {
        String locatorValue = locators.getProperty(locator);
        log.info("Element Name:" + locator+ " is fetched from locators repository having value: " + locatorValue);
        return getLocators(locatorValue);
    }

    public static WebElement getLocator(String locator) {
        String[] split = locator.split(":", 2);
        String locatorType = split[0].toLowerCase();
        String locatorValue = split[1];
        log.info("Locator Type is: {}", locatorType);

        switch (locatorType) {
            case "id":
                return driver.findElement(By.id(locatorValue));
            case "name":
                return driver.findElement(By.name(locatorValue));
            case "classname":
            case "class":
                return driver.findElement(By.className(locatorValue));
            case "tagname":
            case "tag":
                return driver.findElement(By.tagName(locatorValue));
            case "linktext":
            case "link":
                return driver.findElement(By.linkText(locatorValue));
            case "partiallinktext":
                return driver.findElement(By.partialLinkText(locatorValue));
            case "cssselector":
            case "css":
                return driver.findElement(By.cssSelector(locatorValue));
            case "xpath":
                return driver.findElement(By.xpath(locatorValue));
            default:
                Reporter.ReportFail("Fetch Locator","Unable to find locator type:" + locatorType,false);
                return null;
        }
    }

    public static List<WebElement> getLocators(String locator) {
        String[] split = locator.split(":", 2);
        String locatorType = split[0].toLowerCase();
        String locatorValue = split[1];
        log.info("Locator Type is: {}", locatorType);

        switch (locatorType) {
            case "id":
                return driver.findElements(By.id(locatorValue));
            case "name":
                return driver.findElements(By.name(locatorValue));
            case "classname":
            case "class":
                return driver.findElements(By.className(locatorValue));
            case "tagname":
            case "tag":
                return driver.findElements(By.tagName(locatorValue));
            case "linktext":
            case "link":
                return driver.findElements(By.linkText(locatorValue));
            case "partiallinktext":
                return driver.findElements(By.partialLinkText(locatorValue));
            case "cssselector":
            case "css":
                return driver.findElements(By.cssSelector(locatorValue));
            case "xpath":
                return driver.findElements(By.xpath(locatorValue));
            default:
                Reporter.ReportFail("Fetch Locator","Unable to find locator type:" + locatorType,false);
                return null;
        }
    }
}
