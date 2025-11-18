package Use_case_view_weather;

/**
 * Output boundary for the ViewWeather use case.
 * Implemented by the presenter.
 */
public interface ViewWeatherOutputBoundary {

    /** Called when weather information is loaded successfully. */
    void present(ViewWeatherOutputData outputData);

    /** Called when something goes wrong (e.g. API error). */
    void presentError(String message);
}