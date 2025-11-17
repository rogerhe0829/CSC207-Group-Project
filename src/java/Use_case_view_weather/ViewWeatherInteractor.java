package Use_case_view_weather;

import use_case.weather.WeatherDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interactor for the ViewWeather use case (User Stories 2 & 3).
 */
public class ViewWeatherInteractor implements ViewWeatherInputBoundary {

    private final WeatherDataAccessInterface weatherGateway;
    private final ViewWeatherOutputBoundary presenter;

    public ViewWeatherInteractor(WeatherDataAccessInterface weatherGateway,
                                 ViewWeatherOutputBoundary presenter) {
        this.weatherGateway = weatherGateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(ViewWeatherInputData inputData) {
        try {
            WeatherDataAccessInterface.WeatherResult result =
                    weatherGateway.getWeather(inputData.getLat(), inputData.getLon());

            List<ViewWeatherOutputData.DayForecast> days = new ArrayList<>();
            for (WeatherDataAccessInterface.WeatherResult.Daily d : result.dailyForecast) {
                days.add(new ViewWeatherOutputData.DayForecast(
                        d.date,
                        d.maxTemp,
                        d.minTemp,
                        d.description,
                        d.precipitationChance
                ));
            }

            ViewWeatherOutputData outputData = new ViewWeatherOutputData(
                    result.current.temperature,
                    result.current.description,
                    days
            );

            presenter.present(outputData);

        } catch (IOException e) {
            presenter.presentError("Failed to load weather data. Please try again.");
        }
    }
}
