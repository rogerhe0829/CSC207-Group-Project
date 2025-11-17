package entity;

public class SavedItinerary {
    private final String id;
    private final String username;
    private final String origin;
    private final String destination;
    private final TravelSuggestion suggestion;

    public SavedItinerary(String id,
                          String username,
                          String origin,
                          String destination,
                          TravelSuggestion suggestion) {
        this.id = id;
        this.username = username;
        this.origin = origin;
        this.destination = destination;
        this.suggestion = suggestion;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public TravelSuggestion getSuggestion() {
        return suggestion;
    }
}
