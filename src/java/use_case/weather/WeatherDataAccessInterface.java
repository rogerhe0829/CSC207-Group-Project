package use_case.weather;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface WeatherDataAccessInterface {

    WeatherResult getWeather(double lat, double lon) throws IOException;

    class WeatherResult {

        public static class Current {
            public final String temperature;
            public final String description;

            public Current(String temperature, String description) {
                this.temperature = temperature;
                this.description = description;
            }
        }

        public static class Daily {
            public final LocalDate date;
            public final String minTemp;
            public final String maxTemp;
            public final String description;
            public final String precipitationChance;

            public Daily(LocalDate date,
                         String minTemp,
                         String maxTemp,
                         String description,
                         String precipitationChance) {
                this.date = date;
                this.minTemp = minTemp;
                this.maxTemp = maxTemp;
                this.description = description;
                this.precipitationChance = precipitationChance;
            }
        }

        public final Current current;
        public final List<Daily> dailyForecast;

        public WeatherResult(Current current, List<Daily> dailyForecast) {
            this.current = current;
            this.dailyForecast = dailyForecast;
        }
    }
}
