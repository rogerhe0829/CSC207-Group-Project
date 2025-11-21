package usecase.manage_stop;
import java.util.List;

/**
 * Output Data that summarizes the updated list of stops.
 */
public class ManageStopOutputData {

    public static class StopSummary {

        private final String cityName;
        private final int dayIndex;
        private final int order;
        private final String notes;

        public StopSummary(String cityName, int dayIndex, int order, String notes) {
            this.cityName = cityName;
            this.dayIndex = dayIndex;
            this.order = order;
            this.notes = notes;
        }

        public String getCityName() {
            return cityName;
        }

        public int getDayIndex() {
            return dayIndex;
        }

        public int getOrder() {
            return order;
        }

        public String getNotes() {
            return notes;
        }
    }

    private final List <StopSummary> stops;
    private final String message;

    public ManageStopOutputData(List <StopSummary> stops, String message) {
        this.stops = stops;
        this.message = message;
    }

    public List <StopSummary> getStops() {
        return stops;
    }

    public String getMessage() {
        return message;
    }
}
