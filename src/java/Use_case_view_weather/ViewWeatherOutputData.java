package Use_case_view_weather;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO sent from the interactor to the presenter.
 */
public class ViewWeatherOutputData {

    private final String currentTemperature;
    private final String currentSummary;
    private final List<DayForecast> forecast;

    public ViewWeatherOutputData(String currentTemperature,
                                 String currentSummary,
                                 List<DayForecast> forecast) {
        this.currentTemperature = currentTemperature;
        this.currentSummary = currentSummary;
        this.forecast = forecast;
    }

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public String getCurrentSummary() {
        return currentSummary;
    }

    public List<DayForecast> getForecast() {
        return forecast;
    }

    /**
     * DTO for a single forecast day.
     */
    public static class DayForecast {
        private final LocalDate date;
        private final String high;
        private final String low;
        private final String description;
        private final String precipitationChance;

        public DayForecast(LocalDate date,
                           String high,
                           String low,
                           String description,
                           String precipitationChance) {
            this.date = date;
            this.high = high;
            this.low = low;
            this.description = description;
            this.precipitationChance = precipitationChance;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getHigh() {
            return high;
        }

        public String getLow() {
            return low;
        }

        public String getDescription() {
            return description;
        }

        public String getPrecipitationChance() {
            return precipitationChance;
        }
    }
}
