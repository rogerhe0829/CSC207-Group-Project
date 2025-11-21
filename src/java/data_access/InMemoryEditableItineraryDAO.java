package data_access;
import model.Itinerary;

/**
 * Simple in-memory storage for the current editable itinerary.
 */
public class InMemoryEditableItineraryDAO implements EditableItineraryDataAccessInterface {

    private Itinerary itinerary;

    public InMemoryEditableItineraryDAO() {
        this.itinerary = new Itinerary("My Trip");
    }

    @Override
    public Itinerary getItinerary() {
        return itinerary;
    }

    @Override
    public void saveItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
}
