package usecase.manage_stop;
import data_access.EditableItineraryDataAccessInterface;
import model.Itinerary;
import model.Stop;
import java.util.ArrayList;
import java.util.List;

/**
 * Interactor for User Story 9 - "As a user, I want to reorder or delete stops easily, so that I can refine my plan"
 */

public class ManageStopInteractor implements ManageStopInputBoundary {

    private final EditableItineraryDataAccessInterface editableItineraryDAO;
    private final ManageStopOutputBoundary presenter;

    public ManageStopInteractor(EditableItineraryDataAccessInterface editableItineraryDAO,
                                ManageStopOutputBoundary presenter) {
        this.editableItineraryDAO = editableItineraryDAO;
        this.presenter = presenter;
    }

    @Override
    public void execute(ManageStopInputData inputData) {
        Itinerary itinerary = editableItineraryDAO.getItinerary();

        if (itinerary == null) {
            presenter.prepareFailureView("No itinerary found.");
            return;
        }

        List<Stop> stops = new ArrayList<>(itinerary.getStops());
        if (stops.isEmpty()) {
            presenter.prepareFailureView("No stops to modify.");
            return;
        }

        int stopIndex = inputData.getStopIndex();
        if (stopIndex < 0 || stopIndex >= stops.size()) {
            presenter.prepareFailureView("Invalid stop index (Out of bounds).");
            return;
        }

        String message;

        if (inputData.getAction() == ManageStopAction.REORDER) {
            Integer newIndex = inputData.getNewIndex();
            if (newIndex == null) {
                presenter.prepareFailureView("New index is needing for reordering.");
                return;
            }

            if (newIndex < 0 || newIndex >= stops.size()) {
                presenter.prepareFailureView("New index is out of bounds");
                return;
            }

            Stop toMove = stops.remove(stopIndex);
            stops.add(newIndex, toMove);

            for (int i = 0; i < stops.size(); i++) {
                stops.get(i).setOrder(i);
            }

            itinerary.sortStops();
            message = ("Stop reordered!");
        }
        else if (inputData.getAction() == ManageStopAction.DELETE) {
            Stop toRemove = stops.get(stopIndex);

            itinerary.removeStop(toRemove);
            List<Stop> remaining = new ArrayList<>(itinerary.getStops());
            for (int i = 0; i < remaining.size(); i++) {
                remaining.get(i).setOrder(i);
            }
            itinerary.sortStops();
            message = ("Stop deleted!");
        }
        else {
            presenter.prepareFailureView("Invalid action.");
            return;
        }

        editableItineraryDAO.saveItinerary(itinerary);

        List<ManageStopOutputData.StopSummary> summaries = new ArrayList<>();
        for (Stop stop : itinerary.getStops()) {
            summaries.add(new ManageStopOutputData.StopSummary(
                    stop.getPlace().getName(), stop.getDayIndex(),stop.getOrder(), stop.getNotes()));
        }

        ManageStopOutputData outputData = new ManageStopOutputData(summaries, message);
        presenter.prepareSuccessView(outputData);
    }
}
