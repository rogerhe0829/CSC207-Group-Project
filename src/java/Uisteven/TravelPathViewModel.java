package Uisteven;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for TravelPath: wraps a TravelPathState and notifies listeners on changes.
 */
public class TravelPathViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private TravelPathState state = new TravelPathState();

    public TravelPathViewModel() {}

    public TravelPathState getState() {
        return state;
    }

    public void setState(TravelPathState newState) {
        TravelPathState oldState = this.state;
        this.state = newState;
        support.firePropertyChange("state", oldState, newState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
