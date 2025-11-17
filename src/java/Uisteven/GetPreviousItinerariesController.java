package Uisteven;

import usecase.get_previous_itineraries.GetPreviousItinerariesInputBoundary;
import usecase.get_previous_itineraries.GetPreviousItinerariesInputData;

/**
 * Controller：从 View 接收原始数据（比如用户名），
 * 封装成 InputData 然后调用 Interactor。
 */
public class GetPreviousItinerariesController {

    private final GetPreviousItinerariesInputBoundary interactor;

    public GetPreviousItinerariesController(GetPreviousItinerariesInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * 以后 View 只需要调用这个方法，并传一个 username 即可。
     */
    public void execute(String username) {
        GetPreviousItinerariesInputData inputData =
                new GetPreviousItinerariesInputData(username);
        interactor.execute(inputData);
    }
}
