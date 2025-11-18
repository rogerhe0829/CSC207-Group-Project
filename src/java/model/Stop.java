package model;

public class Stop {
    private Place place;
    private int dayIndex;
    private int order;
    private String notes;

    public Stop(Place place, int dayIndex, int order) {
        this.place = place;
        this.dayIndex = dayIndex;
        this.order = order;
        this.notes = "";
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getNotes() {
        return notes == null ? "" : notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        String base = "[Day " + dayIndex + "] " + place.getName();
        if (getNotes().isEmpty()) {
            return base;
        } else {
            return base + "Notes added";
        }
    }
}
