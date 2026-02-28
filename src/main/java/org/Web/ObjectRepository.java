package org.Web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ObjectRepository {
    public static Logger log = LogManager.getLogger(ObjectRepository.class);
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
            log.info("Locators loaded successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
