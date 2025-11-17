package entity;

public class TravelSuggestion {
    private final String timeNeeded;
    private final String weatherSummary;
    private final String optimalPath;
    private final String clothingSuggestion;

    public TravelSuggestion(String timeNeeded,
                            String weatherSummary,
                            String optimalPath,
                            String clothingSuggestion) {
        this.timeNeeded = timeNeeded;
        this.weatherSummary = weatherSummary;
        this.optimalPath = optimalPath;
        this.clothingSuggestion = clothingSuggestion;
    }

    public String getTimeNeeded() {
        return timeNeeded;
    }

    public String getWeatherSummary() {
        return weatherSummary;
    }

    public String getOptimalPath() {
        return optimalPath;
    }

    public String getClothingSuggestion() {
        return clothingSuggestion;
    }
}
