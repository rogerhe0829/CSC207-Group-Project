package usecase.get_previous_itineraries;

/**
 * Input Boundary：View / Controller 通过它调用用例。
 */
public interface GetPreviousItinerariesInputBoundary {

    void execute(GetPreviousItinerariesInputData inputData);
}
