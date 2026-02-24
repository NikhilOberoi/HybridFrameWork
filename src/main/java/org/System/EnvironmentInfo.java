package org.System;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.Browser.BrowserType;

import java.io.File;
import java.io.IOException;


public class EnvironmentInfo {
    //read environment info from .prop / .json file
    final static String environmentFileName = "EnvironmentInfo.json";
    private static JsonNode environmentInfo = readEnvironmentVariables();

    // Get Environment File Path
    public static String getEnvironmentFilePath() {
        String projectPath = System.getProperty("user.dir");
        return projectPath+"\\src\\main\\resources\\" + environmentFileName;
    }

    // Read the Environment File Objects.
    public static JsonNode readEnvironmentVariables(){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode environmentDetails = null;
        try{
            System.out.println("Reading Environment info from : "+getEnvironmentFilePath());
            File file = new File(getEnvironmentFilePath());
            environmentDetails = objectMapper.readTree(file);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return environmentDetails;
    }

    // Read and store environment variables.
    public static String getWebURL(){
        return environmentInfo.get("web_url").asText();
    }

    public static BrowserType getBrowserType(){
        BrowserType type = null;
        switch(environmentInfo.get("browser_type").asText().toLowerCase()){
            case "chrome":
                type =  BrowserType.Chrome;
                break;
            case "chrome headless":
            case "chrome_headless":
            case "chromeheadless":
                type =  BrowserType.ChromeHeadless;
                break;
            case "firefox":
                type =  BrowserType.Firefox;
                break;
            default:
                break;
        }
        return type;
    }
    public static int getWebTimeOut(){
        return environmentInfo.get("web_timeout").asInt();
    }
    public static String getDatabaseHost(){
        return environmentInfo.get("database_host").asText();
    }
    public static String getDatabaseUserName(){
        return environmentInfo.get("database_username").asText();
    }
    public static String getDatabasePassword(){
        return environmentInfo.get("database_password").asText();
    }
    public static String getRestAPIEndPoint(){
        return environmentInfo.get("api_endpoint").asText();
    }
    public static String getReportPath(){
        return environmentInfo.get("report_path").asText();
    }
    public static String getEnvironmentName(){
        return environmentInfo.get("env_name").asText();
    }
    public static String getBuildVersion(){
        return environmentInfo.get("build_version").asText();
    }
    public static String getUserName(){
        return environmentInfo.get("user_name").asText();
    }
    public static String getUserPassword(){
        return environmentInfo.get("user_password").asText();
    }
    public static String getOperatingSystem(){
        return environmentInfo.get("operating_systme").asText();
    }

}
