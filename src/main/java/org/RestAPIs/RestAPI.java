package org.RestAPIs;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
public class RestAPI {

    public void executePostRequest() {
        // Define your JSON payload as a String
        String requestBody = "{ \"name\": \"Buddy\", \"status\": \"available\" }";

        given().contentType(ContentType.MULTIPART)
                .baseUri("https://api.example.com/v2")
                .header("Content-Type", "application/json")
                .auth().oauth2("")
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .statusCode(201); // Asserts that status code is 201 Created
    }
}
