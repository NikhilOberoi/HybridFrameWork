package org.Web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ObjectRepository {

    public static Properties locators = new Properties();
    final static String locatorRepoFileName = "Repository.properties";

    // Get locator Repo File Path
    private static String getLocatorRepoFilePath() {
        String projectPath = System.getProperty("user.dir");
        return projectPath+"\\src\\main\\resources\\" + locatorRepoFileName;
    }

    // Read all locators:
    public static void readAllLocators() {
        File locatorFile  = new File(getLocatorRepoFilePath());
        try{
            FileInputStream inputStream = new FileInputStream(locatorFile);
            locators.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
