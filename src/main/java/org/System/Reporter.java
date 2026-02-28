package org.System;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

import static com.aventstack.extentreports.reporter.configuration.util.IOUtil.createDirectory;
import static org.Browser.ExtendedDriver.driver;

public class Reporter {

    static String reportFolderPath =  System.getProperty("user.dir") + EnvironmentInfo.getReportPath()+ Util.getStringDateTime("YYYY_MM", null) +"\\" + Util.getStringDateTime("dd_MM_YYYY_hh_mm_ss",null);
    static String reportFilePath = reportFolderPath + "\\ExtentReport_" + Util.getStringDateTime("MM_dd_YYYY_hh_mm_ss",null) + ".html";
    static ExtentReports  extent = new ExtentReports();
    static ExtentTest extentTest;
    static ExtentTest extentScenario;
    static int ScreenshotIndex = 0;
    public static void InitializeReport(){
        System.out.println("Report Folder Path:" + reportFolderPath);
        // create Directory
        createDirectory(reportFolderPath);

        // Setup the HTML reporter
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFilePath);

        //Create ExtentReports and attach reporter
        extent.attachReporter(htmlReporter);

        // Set Report Information
        extent.setSystemInfo("Host Name", "Automation Report");
        extent.setSystemInfo("Environment", EnvironmentInfo.getEnvironmentName());
        extent.setSystemInfo("Build Version", EnvironmentInfo.getBuildVersion());
        extent.setSystemInfo("Executer", EnvironmentInfo.getUserName());
        extent.setSystemInfo("OS: ", EnvironmentInfo.getOperatingSystem());
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Test Execution Report");
        htmlReporter.config().setReportName("Automation Testing Report");
        htmlReporter.config().setTimelineEnabled(true);
    }

    // Create Test Scenario in report
    public static void CreateTestScenario(String ScenarioId, String ScenarioDescription){
        extentScenario = extent.createTest(ScenarioId, ScenarioDescription);
    }

    // Create Test Case under Test Scenario
    public static void CreateTestCase(String TestCaseId, String TestCaseDescription){
        extentTest = extentScenario.createNode(TestCaseId, TestCaseDescription);
    }

    public static void ReportPass(String Title, String Message, boolean Screenshot){
        CreateTestCase(Title, Message);

        if(Screenshot)
            extentTest.pass(Title + ": " + Message).addScreenCaptureFromPath(getScreenshotPath());
        else
            extentTest.pass(Title + ": " + Message);
    }

    public static void ReportFail(String Title, String Message, boolean Screenshot){
        if(Screenshot)
            extentTest.fail(Title + ": " + Message).addScreenCaptureFromPath(getScreenshotPath());
        else
            extentTest.fail(Title + ": " + Message);
    }

    //Finalize the report
    public static void FinaliseReport(){
        extent.flush();
    }

    private static String getScreenshotPath() {
        String screenshotPath = reportFolderPath+"\\Screenshots";
        createDirectory(screenshotPath);
        screenshotPath = screenshotPath + "\\image_" + ScreenshotIndex++ +".png";
        System.out.println("Screen shot Path" + screenshotPath);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Use FileHandler to copy the file to a desired location
        try{
            FileHandler.copy(screenshotFile, new File(screenshotPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshotPath;
    }
}