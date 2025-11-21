package usecase.manage_stop;

/**
 * Output Boundary that is implemented by the presenter
 */

public interface ManageStopOutputBoundary {

    void prepareSuccessView(ManageStopOutputData outputData);
    void prepareFailureView(String errorMessage);
}
