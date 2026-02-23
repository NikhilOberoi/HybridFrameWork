package org.Web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectRepository {

    Properties locators = new Properties();
    final static String locatorRepoFileName = "Repository.properties";

    // Get locator Repo File Path
    private static String getLocatorRepoFilePath() {
        String projectPath = System.getProperty("user.dir");
        return projectPath+"\\src\\main\\resources\\" + locatorRepoFileName;
    }

    // Read all locators:
    private void readAllLocators() throws IOException {
        File locatorFile  = new File(getLocatorRepoFilePath());
        FileInputStream inputStream = new FileInputStream(locatorFile);
        locators.load(inputStream);
    }
}
