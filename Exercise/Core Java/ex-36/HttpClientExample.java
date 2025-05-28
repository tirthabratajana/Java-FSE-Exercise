// HttpClientExample.java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class HttpClientExample {
    public static void main(String[] args) {
        // Create an HttpClient instance
        HttpClient client = HttpClient.newHttpClient();

        // Build an HttpRequest for a public API (e.g., GitHub API for user info)
        String apiUrl = "https://api.github.com/users/octocat"; // Example public API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET() // or .POST(HttpRequest.BodyPublishers.ofString("your_json_body")) for POST requests
                .build();

        try {
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response status code
            System.out.println("Response Status Code: " + response.statusCode());

            // Print the response body (which is usually JSON for APIs)
            System.out.println("Response Body:\n" + response.body());

            // Optional: Basic check for a successful response
            if (response.statusCode() == 200) {
                System.out.println("\nSuccessfully fetched data.");
                // Here you would typically parse the JSON body using a library like Jackson or Gson
                // Example (without actual parsing library):
                // System.out.println("Partial data: " + response.body().substring(0, Math.min(response.body().length(), 200)) + "...");
            } else {
                System.err.println("\nFailed to fetch data. Server responded with: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("An error occurred during the HTTP request: " + e.getMessage());
            e.printStackTrace();
        }
    }
}