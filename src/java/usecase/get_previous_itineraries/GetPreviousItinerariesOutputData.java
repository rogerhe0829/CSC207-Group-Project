package usecase.get_previous_itineraries;

import java.util.List;

/**
 * 用来从 Interactor 传给 Presenter 的输出数据：
 * 里面就是一堆「Past Travel 0/1/...」的简要信息。
 */
public class GetPreviousItinerariesOutputData {

    private final List<PreviousItinerarySummary> itineraries;

    public GetPreviousItinerariesOutputData(List<PreviousItinerarySummary> itineraries) {
        this.itineraries = itineraries;
    }

    public List<PreviousItinerarySummary> getItineraries() {
        return itineraries;
    }
}
