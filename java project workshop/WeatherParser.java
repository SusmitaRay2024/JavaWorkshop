// This class is responsible for extracting specific weather values 
// from the raw JSON string received from the API.
public class WeatherParser {

    // Helper method to extract a value from JSON using a given key.
    // It searches for:  "key" : "value"
    private String extract(String json, String key) {

        // Finds the position of the key inside the JSON string
        int keyIndex = json.indexOf("\"" + key + "\"");

        // If key is not found, return N/A
        if (keyIndex == -1) return "N/A";

        // Finds the colon after the key
        int colon = json.indexOf(":", keyIndex);

        // Finds the first double-quote after the colon → beginning of the value
        int firstQuote = json.indexOf("\"", colon + 1);

        // Finds the next double-quote → end of the value
        int secondQuote = json.indexOf("\"", firstQuote + 1);

        // Extracts and returns the value between the quotes
        return json.substring(firstQuote + 1, secondQuote);
    }

    // Extracts temperature in Celsius from JSON
    public String getTemp(String json) {
        return extract(json, "temp_C");
    }

    // Extracts wind speed from JSON
    public String getWindSpeed(String json) {
        return extract(json, "windspeedKmph");
    }

    // Extracts wind direction from JSON
    public String getWindDirection(String json) {
        return extract(json, "winddirDegree");
    }

    // Extracts the observation/local time
    public String getObservationTime(String json) {

        // First try to extract 'localObsDateTime'
        String time = extract(json, "localObsDateTime");

        // If API returns an unexpected format or "iso8601", switch to another key
        if (time.equals("iso8601") || time.equals("N/A")) {

            // Use 'observation_time' as fallback
            time = extract(json, "observation_time");
        }

        // Final extracted time
        return time;
    }

}
