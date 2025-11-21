package data_access;
import model.Itinerary;

/**
 * Gateway for the current editable itinerary
 */
public interface EditableItineraryDataAccessInterface {
    /**
     * Returns the current editable itinerary.
     * Never returns null once initialized
     */
    Itinerary getItinerary();

    /**
     * Saves the updated Itinerary
     */
    void saveItinerary(Itinerary itinerary);
}

