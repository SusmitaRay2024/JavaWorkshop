import java.util.Scanner;

// Main class that controls the entire Weather Application
public class WeatherApp {

    public static void main(String[] args) {

        // Scanner is used to take user input from the console
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Simple Real-Time Weather App ===");

        // Infinite loop → keeps running until user types 'exit'
        while (true) {

            System.out.print("\nEnter city (Delhi, Mumbai, Kolkata, Chennai) or 'exit': ");
            
            // Reads the city name entered by the user
            String city = sc.nextLine();

            // Exit condition → stops the loop and closes the app
            if (city.equalsIgnoreCase("exit")) {
                System.out.println("Closing application...");
                break;
            }

            // Creating an object of WeatherService to fetch weather data
            WeatherService ws = new WeatherService();

            // Sends HTTP request, receives raw JSON string
            String json = ws.getWeather(city);

            // If API returned null → city not supported or error occurred
            if (json == null) {
                System.out.println("City not supported!");
                continue;  // skip and ask again
            }

            // Creating WeatherParser object to extract specific weather details
            WeatherParser parser = new WeatherParser();

            // Extract temperature, wind speed, and wind direction from JSON
            String temp  = parser.getTemp(json);
            String wind  = parser.getWindSpeed(json);
            String dir   = parser.getWindDirection(json);

            // Get local system time as fallback
            String currentTime = WeatherTime.getCurrentLocalTime();

            // Display the weather report
            System.out.println("\n------ WEATHER REPORT ------");
            System.out.println("Temperature : " + temp + "°C");
            System.out.println("Wind Speed  : " + wind + " km/h");
            System.out.println("Wind Dir    : " + dir + "°");

            // Displays current local time
            System.out.println("Local Time  : " + currentTime);

            // (Extra display line – optional)
            System.out.println("Local Time  : " + WeatherTime.getCurrentLocalTime());

            System.out.println("-----------------------------\n");
        }

        // Close scanner to avoid memory leak
        sc.close();
    }
}
