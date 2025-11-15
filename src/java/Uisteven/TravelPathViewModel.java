package Uisteven;

// src/main/java/interface_adapter/travelpath/TravelPathViewModel.java
package interface_adapter.travelpath;

import interface_adapter.ViewModel;

/**
 * TravelPath 的 ViewModel。
 */
public class TravelPathViewModel extends ViewModel<TravelPathState> {

    public static final String VIEW_NAME = "travel path";

    public TravelPathViewModel() {
        super(VIEW_NAME);
        setState(new TravelPathState());
    }

    @Override
    public TravelPathState getState() {
        return super.getState();
    }

    @Override
    public void setState(TravelPathState state) {
        super.setState(state);
    }
}
