package Use_case_view_weather;

/**
 * Input data for the ViewWeather use case.
 * It only needs the latitude and longitude of the destination.
 */
public class ViewWeatherInputData {

    private final double lat;
    private final double lon;

    public ViewWeatherInputData(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
