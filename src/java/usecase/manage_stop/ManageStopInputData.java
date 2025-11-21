package usecase.manage_stop;

/**
 * Input data for managing a stop (either reordering or deleting).
 * stopIndex = index of the stop in the current Itinerary List
 * newIndex = new index for REORDER; ignored for DELETE
 */
public class ManageStopInputData {

    private final int stopIndex;
    private final Integer newIndex;
    private final ManageStopAction action;

    public ManageStopInputData(int stopIndex, Integer newIndex, ManageStopAction action) {
        this.stopIndex = stopIndex;
        this.newIndex = newIndex;
        this.action = action;
    }

    public int getStopIndex() {
        return stopIndex;
    }

    public Integer getNewIndex() {
        return newIndex;
    }

    public ManageStopAction getAction() {
        return action;
    }
}
