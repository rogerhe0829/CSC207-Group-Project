package Uisteven;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TravelPathViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private TravelPathState state = new TravelPathState();

    public TravelPathViewModel() {}

    public TravelPathState getState() {
        return state;
    }

    public void setState(TravelPathState state) {
        this.state = state;
        support.firePropertyChange("state", null, state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
