package Uisteven;
import usecase.manage_stop.ManageStopAction;
import usecase.manage_stop.ManageStopInputBoundary;
import usecase.manage_stop.ManageStopInputData;

/**
 * Controller for User Story 9.
 * The View will call these methods with the selected index.
 */
public class ManageStopController {

    private final ManageStopInputBoundary interactor;

    public ManageStopController(ManageStopInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Reorder a stop from one index to the other.
     */
    public void reorderStop(int fromIndex, int toIndex) {
        ManageStopInputData inputData = new ManageStopInputData(
                fromIndex,
                toIndex,
                ManageStopAction.REORDER
        );
        interactor.execute(inputData);
    }

    /**
     * Delete the stop at the given index.
     */
    public void deleteStop(int index) {
        ManageStopInputData inputData = new ManageStopInputData(
                index,
                null,
                ManageStopAction.DELETE
        );
        interactor.execute(inputData);
    }
}
