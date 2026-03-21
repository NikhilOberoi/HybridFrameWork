package org.Web;

import org.System.Reporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import static org.Browser.ExtendedDriver.driver;
import static org.Web.ObjectRepository.locators;

public class Element {

    public static Logger log = LogManager.getLogger(Element.class);
    public static WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));;

    //Explicit Conditions of Type - WebElement
    public static WebElement ExpectedCondition(String locator, String expectedCondition){

        switch (expectedCondition){
            case "elementToBeClickable" : return Wait.until(ExpectedConditions.elementToBeClickable(getLocator(locator)));
            default : return null;
        }
    }

    //Explicit Conditions of Type - WebElement
    public static boolean ExpectedConditions(String locator, String expectedCondition){
        switch (expectedCondition){
            case "elementToBeClickable" : return Wait.until(ExpectedConditions.elementToBeSelected(getLocator(locator)));
            default : return false;
        }
    }

    // Get Element against provided locator name
    public static WebElement getWebElement(String locator)  {
        return driver.findElement(getLocator(locator));
    }

    // Get Elements against provided locator name
    public static List<WebElement> getWebElements(String locator) {
        return driver.findElements(getLocator(locator));
    }

    public static By getLocator(String locator) {
        String locatorVal = locators.getProperty(locator);
        log.info("Element Name:{}",  locator+  " is fetched from locators repository having value: {}", locatorVal);
        String[] split = locatorVal.split(":", 2);
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