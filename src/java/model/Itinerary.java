package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Itinerary {
    private String title;
    private LocalDate startDate;
    private final List<Stop> stops;

    public Itinerary(String title) {
        this.title = title;
        this.startDate = null;
        this.stops = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<Stop> getStops() {
        return Collections.unmodifiableList(stops);
    }

    public void addStop(Stop stop) {
        stops.add(stop);
    }

    public void removeStop(Stop stop) {
        stops.remove(stop);
    }

    public void sortStops() {
        stops.sort((a, b) -> {
            if (a.getDayIndex() != b.getDayIndex()) {
                return Integer.compare(a.getDayIndex(), b.getDayIndex());
            }
            return Integer.compare(a.getOrder(), b.getOrder());
        });
    }
}
