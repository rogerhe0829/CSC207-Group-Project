package usecase.get_previous_itineraries;

/**
 * Output Boundary：Interactor 通过它把结果交给 Presenter。
 */
public interface GetPreviousItinerariesOutputBoundary {

    void prepareSuccessView(GetPreviousItinerariesOutputData outputData);

    void prepareFailView(String errorMessage);
}
