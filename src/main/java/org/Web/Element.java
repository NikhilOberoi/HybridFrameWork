package org.Web;

import org.System.Reporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import static org.Browser.ExtendedDriver.Wait;
import static org.Browser.ExtendedDriver.driver;
import static org.Web.ObjectRepository.locators;

public class Element {

    public static Logger log = LogManager.getLogger(Element.class);

    //Explicit Condition - Element to be clickable.
    public static WebElement elementToBeClickable(String locator){
        return Wait.until(ExpectedConditions.elementToBeClickable(getLocator(locators.getProperty(locator))));
    }

    // Get Element against provided locator name
    public static WebElement getWebElement(String locator)  {
        String locatorValue = locators.getProperty(locator);
        log.info("Element Name:" + locator+ " is fetched from locators repository having value: " + locatorValue);
        return driver.findElement(getLocator(locatorValue));
    }

    // Get Elements against provided locator name
    public static List<WebElement> getWebElements(String locator) {
        String locatorValue = locators.getProperty(locator);
        log.info("Element Name:" + locator+ " is fetched from locators repository having value: " + locatorValue);
        return driver.findElements(getLocator(locatorValue));
    }

    public static By getLocator(String locator) {
        String[] split = locator.split(":", 2);
        String locatorType = split[0].toLowerCase();
        String locatorValue = split[1];
        log.info("Fetching locator having type: {}", locatorType + " and  value: " + locatorValue);
        switch (locatorType) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "classname":
            case "class":
                return By.className(locatorValue);
            case "tagname":
            case "tag":
                return By.tagName(locatorValue);
            case "linktext":
            case "link":
                return By.linkText(locatorValue);
            case "partiallinktext":
                return By.partialLinkText(locatorValue);
            case "cssselector":
            case "css":
                return By.cssSelector(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            default:
                log.error("Unable to fetch locator having type: {}",locatorType);
                Reporter.ReportFail("Fetch Locator","Unable to find locator type:" + locatorType,false);
                return null;
        }
    }
}