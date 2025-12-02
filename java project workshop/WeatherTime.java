import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// This class provides the current local time of the system.
// It is used when the API does not return observation time properly.
public class WeatherTime {

    // Static method that returns formatted current date and time
    public static String getCurrentLocalTime() {

        // Creates a formatter to convert date/time into readable format.
        // Example Output: "25-11-2024 10:45 AM"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

        // Fetches the current local system time and formats it using the formatter
        return LocalDateTime.now().format(formatter);
    }
}
