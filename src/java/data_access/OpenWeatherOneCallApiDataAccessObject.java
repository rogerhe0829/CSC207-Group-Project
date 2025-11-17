package data_access;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.weather.WeatherDataAccessInterface;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * OpenWeather One Call 3.0 data access implementation.
 */
public class OpenWeatherOneCallApiDataAccessObject implements WeatherDataAccessInterface {

    private static final String BASE_URL = "https://api.openweathermap.org/data/3.0/onecall";

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey;

    public OpenWeatherOneCallApiDataAccessObject(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public WeatherResult getWeather(double lat, double lon) throws IOException {
        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder()
                .addQueryParameter("lat", String.valueOf(lat))
                .addQueryParameter("lon", String.valueOf(lon))
                .addQueryParameter("exclude", "minutely,hourly,alerts")
                .addQueryParameter("units", "metric")
                .addQueryParameter("appid", apiKey)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("OpenWeather error: " + response.code());
            }

            String body = response.body().string();
            JSONObject json = new JSONObject(body);

            // current
            JSONObject currentJson = json.getJSONObject("current");
            double temp = currentJson.getDouble("temp");
            JSONArray weatherArr = currentJson.getJSONArray("weather");
            String description = weatherArr.getJSONObject(0).getString("description");

            WeatherResult.Current current =
                    new WeatherResult.Current(
                            temp + " °C",
                            description
                    );

            // daily forecast
            JSONArray dailyArr = json.getJSONArray("daily");
            List<WeatherResult.Daily> dailyList = new ArrayList<>();

            int days = Math.min(7, dailyArr.length());
            for (int i = 0; i < days; i++) {
                JSONObject dayJson = dailyArr.getJSONObject(i);

                long dt = dayJson.getLong("dt");
                LocalDate date = Instant.ofEpochSecond(dt)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                JSONObject tempJson = dayJson.getJSONObject("temp");
                double min = tempJson.getDouble("min");
                double max = tempJson.getDouble("max");

                JSONArray wArr = dayJson.getJSONArray("weather");
                String desc = wArr.getJSONObject(0).getString("description");

                double pop = dayJson.optDouble("pop", 0.0);
                String popText = (int) Math.round(pop * 100) + "%";

                dailyList.add(new WeatherResult.Daily(
                        date,
                        min + " °C",
                        max + " °C",
                        desc,
                        popText
                ));
            }

            return new WeatherResult(current, dailyList);
        }
    }
}
