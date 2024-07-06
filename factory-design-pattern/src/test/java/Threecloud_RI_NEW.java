
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class Threecloud_RI_NEW {

    @Test
    public void getTokenAndGetData() {
        // Hit the Post API to Generate Bearer Token
        RestAssured.baseURI = "https://login.microsoftonline.com/3cloudsolutions.com/oauth2/token";

        Response tokenResponse = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("resource", "https://graph.windows.net")
                .formParam("client_id", "66bd08c9-daa1-415b-972d-f1b07bbba6d9")
                .formParam("client_secret", "dzhQc4nrGaLoWv+mbYmYPkJPsWJk15ENnH0xYXf9Mc8=")
                .when()
                .post("?api-version=1.0");

        String accessToken = tokenResponse.jsonPath().getString("access_token");
        System.out.println("Access Token: " + accessToken);
        tokenResponse.then().statusCode(200);
        System.out.println("Token generated successfully");

        String initialEndpoint = "https://api.partnercenter.microsoft.com/v1/invoices/unbilled/lineitems";

        // Placeholder for storing all response bodies
        StringBuilder fullResponse = new StringBuilder();

        // Pagination loop variables
        String continuationToken = null;
        boolean hasNextPage = true;
        boolean notFirstTime = false;
        int counter = 1;
        // Pagination loop
        while (hasNextPage) {
            // Build request with pagination token if available
            RequestSpecification request = RestAssured.given()
                    .header("Authorization", "Bearer " + accessToken)
                    .queryParam("provider", "OneTime")
                    .queryParam("invoicelineitemtype", "billingLineItems")
                    .queryParam("currencycode", "USD")
                    .queryParam("period", "previous");
            if (notFirstTime) {
                request.queryParam("size", 2000);
                request.queryParam("seekOperation", "next");
                request.header("MS-ContinuationToken", continuationToken);
            }
            // Execute GET request
            Response dataResponse = request.get(initialEndpoint);

            // Extract response body
            String responseBody = dataResponse.getBody().asString();
            fullResponse.append(responseBody).append("\n");

            // Check if there is a continuation token for the next page
            JSONObject jsonObject = new JSONObject(responseBody);
            String nextLink = "";
            if (jsonObject.has("links")) {
                JSONObject link = jsonObject.getJSONObject("links");
                JSONObject next = link.getJSONObject("next");
                nextLink = next.getString("uri");
                System.out.println("nextLink: " + nextLink);
            }
            String continuationTokenTemp = null;
            if (jsonObject.has("continuationToken")) {
                continuationTokenTemp = jsonObject.getString("continuationToken");
                System.out.println("continuationTokenTemp------" + continuationTokenTemp);
            }
            if (continuationTokenTemp != null && continuationTokenTemp.length() > 0) {
                continuationToken = continuationTokenTemp;
            } else {
                hasNextPage = false;
            }

            notFirstTime = true;

            System.out.println("Has Next Page: " + hasNextPage);

            String filePath = "E:\\gurjeet-code\\3cloud-" + counter++ + ".json"; // Specify the file path
            try {
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write(fullResponse.toString());
                fileWriter.close();
                System.out.println("Response saved to file successfully: " + filePath);
            } catch (IOException e) {
                System.err.println("Error saving response to file: " + e.getMessage());
            }

            fullResponse.setLength(0);
        }

    }

}
