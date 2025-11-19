public class WeatherParser {

    // Extracts value: "key" : "value"
    private String extract(String json, String key) {
        int keyIndex = json.indexOf("\"" + key + "\"");
        if (keyIndex == -1) return "N/A";

        int colon = json.indexOf(":", keyIndex);
        int firstQuote = json.indexOf("\"", colon + 1);
        int secondQuote = json.indexOf("\"", firstQuote + 1);

        return json.substring(firstQuote + 1, secondQuote);
    }

    public String getTemp(String json) {
        return extract(json, "temp_C");
    }

    public String getWindSpeed(String json) {
        return extract(json, "windspeedKmph");
    }

    public String getWindDirection(String json) {
        return extract(json, "winddirDegree");
    }

    public String getObservationTime(String json) {
    String time = extract(json, "localObsDateTime");

    // If API gives 'iso8601', switch to observation_time
    if (time.equals("iso8601") || time.equals("N/A")) {
        time = extract(json, "observation_time");
    }

    return time;
}

}
