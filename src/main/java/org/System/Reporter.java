package org.System;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reporter {

    static String reportFolderPath =  System.getProperty("user.dir") + EnvironmentInfo.getReportPath()+ Util.getStringDateTime("YYYY_MM", null);
    static String reportFilePath = reportFolderPath + "\\ExtentReport_" + Util.getStringDateTime("MM_dd_YYYY_hh_mm_ss",null) + ".html";


    public static void initializeReport() throws IOException {
        // create Directory
        createReportDirectory();

        // Setup the HTML reporter
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFilePath);

        //Create ExtentReports and attach reporter
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Set Report Innformation
        extent.setSystemInfo("Host Name", "Automation Report");
        extent.setSystemInfo("Environment", EnvironmentInfo.getEnvironmentName());
        extent.setSystemInfo("Build Version", EnvironmentInfo.getBuildVersion());
        extent.setSystemInfo("Executer", EnvironmentInfo.getUserName());
        extent.setSystemInfo("OS: ", EnvironmentInfo.getOperatingSystem());

        htmlReporter.config().setDocumentTitle("Test Execution Report");
        htmlReporter.config().setReportName("Automation Testing Report");
        //htmlReporter.config().setChartVisibilityOnOpen(true);
    }


    private static void createReportDirectory() throws IOException {
        File dir = new File(reportFolderPath);
        if (!dir.exists())
            dir.mkdir();
    }
}
