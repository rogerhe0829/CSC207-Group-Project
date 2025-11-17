package usecase.get_previous_itineraries;

/**
 * 展示在“Past Travel 0/1...” 列表里的简要信息。
 */
public class PreviousItinerarySummary {

    private final String id;
    private final String origin;
    private final String destination;

    public PreviousItinerarySummary(String id, String origin, String destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }
}
