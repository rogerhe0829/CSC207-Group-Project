package Uisteven;

// src/main/java/interface_adapter/travelpath/SearchDestinationController.java
package interface_adapter.travelpath;

import use_case.search_destination.SearchDestinationInputBoundary;
import use_case.search_destination.SearchDestinationInputData;

/**
 * 从 View 接收目的地字符串，交给 SearchDestination 用例。
 */
public class SearchDestinationController {

    private final SearchDestinationInputBoundary interactor;

    public SearchDestinationController(SearchDestinationInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String rawDestination) {
        SearchDestinationInputData inputData =
                new SearchDestinationInputData(rawDestination);
        interactor.execute(inputData);
    }
}
