package Uisteven;

// src/main/java/interface_adapter/travelpath/TravelPathState.java
package interface_adapter.travelpath;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TravelPath 界面需要的全部状态。
 */
public class TravelPathState {

    // 搜索相关
    private String destinationQuery = "";
    private String resolvedDestinationName = "";

    // 当前目的地经纬度
    private Double currentLat;
    private Double currentLon;

    // 当前天气
    private String currentWeatherSummary = "";
    private String currentTemperature = "";

    // 行程 & stops
    private List<ItineraryStop> stops = new ArrayList<>();
    private int selectedStopIndex = -1;

    // 7 天预报
    private List<ForecastDay> forecast = new ArrayList<>();

    // 路线信息（Google Routes）
    private String lastRouteDistanceText = "";
    private String lastRouteDurationText = "";
    private String lastRouteSummary = "";

    // 错误信息
    private String errorMessage = "";

    // ===== getters / setters =====

    public String getDestinationQuery() {
        return destinationQuery;
    }

    public void setDestinationQuery(String destinationQuery) {
        this.destinationQuery = destinationQuery;
    }

    public String getResolvedDestinationName() {
        return resolvedDestinationName;
    }

    public void setResolvedDestinationName(String resolvedDestinationName) {
        this.resolvedDestinationName = resolvedDestinationName;
    }

    public Double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(Double currentLat) {
        this.currentLat = currentLat;
    }

    public Double getCurrentLon() {
        return currentLon;
    }

    public void setCurrentLon(Double currentLon) {
        this.currentLon = currentLon;
    }

    public String getCurrentWeatherSummary() {
        return currentWeatherSummary;
    }

    public void setCurrentWeatherSummary(String currentWeatherSummary) {
        this.currentWeatherSummary = currentWeatherSummary;
    }

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public List<ItineraryStop> getStops() {
        return stops;
    }

    public void setStops(List<ItineraryStop> stops) {
        this.stops = stops;
    }

    public int getSelectedStopIndex() {
        return selectedStopIndex;
    }

    public void setSelectedStopIndex(int selectedStopIndex) {
        this.selectedStopIndex = selectedStopIndex;
    }

    public List<ForecastDay> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastDay> forecast) {
        this.forecast = forecast;
    }

    public String getLastRouteDistanceText() {
        return lastRouteDistanceText;
    }

    public void setLastRouteDistanceText(String lastRouteDistanceText) {
        this.lastRouteDistanceText = lastRouteDistanceText;
    }

    public String getLastRouteDurationText() {
        return lastRouteDurationText;
    }

    public void setLastRouteDurationText(String lastRouteDurationText) {
        this.lastRouteDurationText = lastRouteDurationText;
    }

    public String getLastRouteSummary() {
        return lastRouteSummary;
    }

    public void setLastRouteSummary(String lastRouteSummary) {
        this.lastRouteSummary = lastRouteSummary;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // ===== 内部简单类型：Stop & ForecastDay =====

    public static class ItineraryStop {
        private String city;
        private LocalDate day;
        private String notes;

        public ItineraryStop() {}

        public ItineraryStop(String city, LocalDate day, String notes) {
            this.city = city;
            this.day = day;
            this.notes = notes;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public LocalDate getDay() {
            return day;
        }

        public void setDay(LocalDate day) {
            this.day = day;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    }

    public static class ForecastDay {
        private LocalDate date;
        private String summary;
        private String highTemperature;
        private String lowTemperature;
        private String precipitationChance;

        public ForecastDay() {}

        public ForecastDay(LocalDate date,
                           String summary,
                           String highTemperature,
                           String lowTemperature,
                           String precipitationChance) {
            this.date = date;
            this.summary = summary;
            this.highTemperature = highTemperature;
            this.lowTemperature = lowTemperature;
            this.precipitationChance = precipitationChance;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getHighTemperature() {
            return highTemperature;
        }

        public void setHighTemperature(String highTemperature) {
            this.highTemperature = highTemperature;
        }

        public String getLowTemperature() {
            return lowTemperature;
        }

        public void setLowTemperature(String lowTemperature) {
            this.lowTemperature = lowTemperature;
        }

        public String getPrecipitationChance() {
            return precipitationChance;
        }

        public void setPrecipitationChance(String precipitationChance) {
            this.precipitationChance = precipitationChance;
        }
    }
}

