import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Simple Real-Time Weather App ===");

        while (true) {
            System.out.print("\nEnter city (Delhi, Mumbai, Kolkata, Chennai) or 'exit': ");
            String city = sc.nextLine();

            if (city.equalsIgnoreCase("exit")) {
                System.out.println("Closing application...");
                break;
            }

            WeatherService ws = new WeatherService();
            String json = ws.getWeather(city);

            if (json == null) {
                System.out.println("City not supported!");
                continue;
            }

            WeatherParser parser = new WeatherParser();

            // FIXED → use json, not jsonData
            String temp  = parser.getTemp(json);
            String wind  = parser.getWindSpeed(json);
            String dir   = parser.getWindDirection(json);
            String currentTime = WeatherTime.getCurrentLocalTime();

            System.out.println("\n------ WEATHER REPORT ------");
            System.out.println("Temperature : " + temp + "°C");
            System.out.println("Wind Speed  : " + wind + " km/h");
            System.out.println("Wind Dir    : " + dir + "°");
            System.out.println("Local Time  : " + currentTime);
            System.out.println("Local Time  : " + WeatherTime.getCurrentLocalTime());

            System.out.println("-----------------------------\n");


        }

        sc.close();
    }
}

