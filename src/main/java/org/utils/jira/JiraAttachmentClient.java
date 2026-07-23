package org.utils.jira;
import okhttp3.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JiraAttachmentClient {

    private final String jiraBaseUrl;
    private final String authHeader;
    private final OkHttpClient client;

    public JiraAttachmentClient(String jiraBaseUrl, String email, String apiToken) {
        this.jiraBaseUrl = jiraBaseUrl;
        String credentials = email + ":" + apiToken;
        this.authHeader = "Basic " + Base64.getEncoder()
                .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        this.client = new OkHttpClient();
    }

    public void uploadAttachment(String issueKey, String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }

        RequestBody fileBody = RequestBody.create(
                file,
                MediaType.parse("application/octet-stream")
        );

        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .build();

        Request request = new Request.Builder()
                .url(jiraBaseUrl + "/rest/api/3/issue/" + issueKey + "/attachments")
                .addHeader("Authorization", authHeader)
                .addHeader("Accept", "application/json")
                .addHeader("X-Atlassian-Token", "no-check")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorBody = response.body() != null ? response.body().string() : "No response body";
                throw new RuntimeException(
                        "Jira attachment upload failed. HTTP Code: "
                                + response.code() + ", Response: " + errorBody
                );
            }
        }
    }
}

java
package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.jira.JiraAttachmentClient;

public class JiraFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            String issueKey = getIssueKey(result);
            String screenshotPath = getScreenshotPath(result);
            String logPath = getLogPath(result);

            JiraAttachmentClient jiraClient = new JiraAttachmentClient(
                    System.getProperty("jira.url"),
                    System.getProperty("jira.email"),
                    System.getProperty("jira.token")
            );

            if (issueKey != null && !issueKey.isBlank()) {
                jiraClient.uploadAttachment(issueKey, screenshotPath);
                jiraClient.uploadAttachment(issueKey, logPath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getIssueKey(ITestResult result) {
        // Option 1: read from @Test description or custom annotation
        // Option 2: map from test data / Excel / JSON / feature tags
        // Example:
        return "QA-123";
    }

    private String getScreenshotPath(ITestResult result) {
        return "test-output/screenshots/" + result.getName() + ".png";
    }

    private String getLogPath(ITestResult result) {
        return "test-output/logs/" + result.getName() + ".log";
    }
}