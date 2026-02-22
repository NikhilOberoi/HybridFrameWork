package Test.Web;

import org.Browser.ExtendedDriver;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    static WebDriver driver = ExtendedDriver.driver;

    public static void main(String[] args){
        System.out.println("driver.getTitle()");
        System.out.println(ExtendedDriver.driver.getTitle());
       driver.navigate().refresh();
    }
}
