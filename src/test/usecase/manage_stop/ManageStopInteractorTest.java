package usecase.manage_stop;

import data_access.EditableItineraryDataAccessInterface;
import model.Itinerary;
import model.Place;
import model.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManageStopInteractorTest {

    private TestEditableItineraryDAO dao;
    private TestPresenter presenter;
    private ManageStopInteractor interactor;

    @BeforeEach
    void setUp() {
        dao = new TestEditableItineraryDAO();
        presenter = new TestPresenter();
        interactor = new ManageStopInteractor(dao, presenter);
    }

    @Test
    void reorderStop_success() {
        Itinerary itinerary = new Itinerary("Trip");
        Place toronto = new Place("toronto", "Toronto", 43.7, -79.4);
        Place montreal = new Place("montreal", "Montreal", 45.5, -73.6);

        Stop stop0 = new Stop(toronto, 0, 0);
        Stop stop1 = new Stop(montreal, 0, 1);
        itinerary.addStop(stop0);
        itinerary.addStop(stop1);
        dao.setItinerary(itinerary);

        ManageStopInputData input = new ManageStopInputData(
                1,
                0,
                ManageStopAction.REORDER
        );
        interactor.execute(input);

        assertTrue(presenter.lastSuccess);
        assertNull(presenter.lastErrorMessage);
        assertNotNull(presenter.lastOutputData);

        List<ManageStopOutputData.StopSummary> stops = presenter.lastOutputData.getStops();
        assertEquals(2, stops.size());
        assertEquals("Montreal", stops.get(0).getCityName());
        assertEquals("Toronto", stops.get(1).getCityName());
    }

    @Test
    void deleteStop_success() {
        Itinerary itinerary = new Itinerary("Trip");
        Place toronto = new Place("toronto", "Toronto", 43.7, -79.4);
        Place montreal = new Place("montreal", "Montreal", 45.5, -73.6);

        Stop stop0 = new Stop(toronto, 0, 0);
        Stop stop1 = new Stop(montreal, 0, 1);
        itinerary.addStop(stop0);
        itinerary.addStop(stop1);
        dao.setItinerary(itinerary);

        ManageStopInputData input = new ManageStopInputData(
                0,
                null,
                ManageStopAction.DELETE
        );
        interactor.execute(input);

        assertTrue(presenter.lastSuccess);
        assertNull(presenter.lastErrorMessage);
        assertNotNull(presenter.lastOutputData);

        List<ManageStopOutputData.StopSummary> stops = presenter.lastOutputData.getStops();
        assertEquals(1, stops.size());
        assertEquals("Montreal", stops.get(0).getCityName());
    }

    @Test
    void itineraryIsNull_failure() {
        dao.setItinerary(null);

        ManageStopInputData input = new ManageStopInputData(
                0,
                null,
                ManageStopAction.DELETE
        );

        interactor.execute(input);

        assertFalse(presenter.lastSuccess);
        assertNotNull(presenter.lastErrorMessage);
        assertNull(presenter.lastOutputData);
    }

    @Test
    void emptyItinerary_failure() {
        Itinerary itinerary = new Itinerary("Empty Trip");
        dao.setItinerary(itinerary);

        ManageStopInputData input = new ManageStopInputData(
                0,
                null,
                ManageStopAction.DELETE
        );

        interactor.execute(input);

        assertFalse(presenter.lastSuccess);
        assertNotNull(presenter.lastErrorMessage);
        assertNull(presenter.lastOutputData);
    }

    @Test
    void invalidStopIndex_failure() {
        Itinerary itinerary = new Itinerary("Trip");
        itinerary.addStop(new Stop(new Place("toronto", "Toronto", 43.7, -79.4), 0, 0));
        dao.setItinerary(itinerary);

        ManageStopInputData input = new ManageStopInputData(
                5,
                null,
                ManageStopAction.DELETE
        );
        interactor.execute(input);

        assertFalse(presenter.lastSuccess);
        assertNotNull(presenter.lastErrorMessage);
        assertNull(presenter.lastOutputData);
    }

    @Test
    void reorderWithoutNewIndex_failure() {
        Itinerary itinerary = new Itinerary("Trip");
        itinerary.addStop(new Stop(new Place("toronto", "Toronto", 43.7, -79.4), 0, 0));
        dao.setItinerary(itinerary);

        ManageStopInputData input = new ManageStopInputData(
                0,
                null,
                ManageStopAction.REORDER
        );
        interactor.execute(input);

        assertFalse(presenter.lastSuccess);
        assertNotNull(presenter.lastErrorMessage);
        assertNull(presenter.lastOutputData);
    }

    @Test
    void reorderWithOutOfBoundsNewIndex_failure() {
        Itinerary itinerary = new Itinerary("Trip");
        itinerary.addStop(new Stop(new Place("toronto", "Toronto", 43.7, -79.4), 0, 0));
        itinerary.addStop(new Stop(new Place("montreal", "Montreal", 45.5, -73.6), 0, 1));
        dao.setItinerary(itinerary);

        ManageStopInputData input = new ManageStopInputData(
                0,
                5,
                ManageStopAction.REORDER
        );
        interactor.execute(input);

        assertFalse(presenter.lastSuccess);
        assertNotNull(presenter.lastErrorMessage);
        assertNull(presenter.lastOutputData);
    }

    @Test
    void unknownAction_failure() {
        Itinerary itinerary = new Itinerary("Trip");
        itinerary.addStop(new Stop(new Place("toronto", "Toronto", 43.7, -79.4), 0, 0));
        dao.setItinerary(itinerary);

        ManageStopInputData input = new ManageStopInputData(
                0,
                null,
                null
        );
        interactor.execute(input);

        assertFalse(presenter.lastSuccess);
        assertNotNull(presenter.lastErrorMessage);
        assertNull(presenter.lastOutputData);
    }

    private static class TestEditableItineraryDAO implements EditableItineraryDataAccessInterface {

        private Itinerary itinerary;

        @Override
        public Itinerary getItinerary() {
            return itinerary;
        }

        @Override
        public void saveItinerary(Itinerary itinerary) {
            this.itinerary = itinerary;
        }

        public void setItinerary(Itinerary itinerary) {
            this.itinerary = itinerary;
        }
    }

    private static class TestPresenter implements ManageStopOutputBoundary {

        private boolean lastSuccess;
        private String lastErrorMessage;
        private ManageStopOutputData lastOutputData;

        @Override
        public void prepareSuccessView(ManageStopOutputData outputData) {
            lastSuccess = true;
            lastErrorMessage = null;
            lastOutputData = outputData;
        }

        @Override
        public void prepareFailureView(String errorMessage) {
            lastSuccess = false;
            lastErrorMessage = errorMessage;
            lastOutputData = null;
        }
    }
}


