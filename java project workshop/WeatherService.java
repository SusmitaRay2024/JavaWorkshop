import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// This class handles all API-related tasks.
// It sends an HTTP GET request to wttr.in and returns the raw JSON response.
public class WeatherService {

    // Method to fetch weather data for a given city
    public String getWeather(String city) {
        try {
            // Constructing the API URL dynamically based on the city name
            String urlString = "https://wttr.in/" + city + "?format=j1";

            // Create a URL object
            URL url = new URL(urlString);

            // Open an HTTP connection to the API URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the request type → GET request
            conn.setRequestMethod("GET");

            // BufferedReader reads the API response line by line
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );

            // StringBuilder stores the full JSON response
            StringBuilder sb = new StringBuilder();
            String line;

            // Read the response until no more lines are left
            while ((line = reader.readLine()) != null) {
                sb.append(line);  // Append each line to the StringBuilder
            }

            // Close the reader after use
            reader.close();

            // Return the complete JSON string
            return sb.toString();

        } catch (IOException e) {
            // If there is an error (invalid city, no internet, etc.) return null
            return null;
        }
    }
}
