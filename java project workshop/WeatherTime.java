import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherTime {

    public static String getCurrentLocalTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        return LocalDateTime.now().format(formatter);
    }
}

