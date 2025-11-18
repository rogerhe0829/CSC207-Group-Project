package model;

import java.util.ArrayList;
import java.util.List;

public class PackingTipGenerator {
    public static List<String> generate(WeatherData weather) {
        List<String> tips = new ArrayList<>();

        double temp = weather.getTemp();
        String cond = weather.getCondition();

        if (cond.contains("rain") || weather.getRainVolume() > 0.1) {
            tips.add("Umbrella");
            tips.add("Waterproof jacket");
        }

        if (cond.contains("snow") || weather.getSnowVolume() > 0.1) {
            tips.add("Snow boots");
            tips.add("Gloves");
        }

        if (temp < 5) {
            tips.add("Warm coat");
            tips.add("Scarf");
        }

        if (temp > 28) {
            tips.add("Sunscreen");
            tips.add("Cap or sunhat");
            tips.add("Lightweight clothing");
        }

        if (tips.isEmpty()) {
            tips.add("Comfortable walking shoes");
            tips.add("Reusable water bottle");
        }

        return tips;
    }
}
