package model;

public class WeatherData {
    private final double temp;
    private final String condition;
    private final double rainVolume;
    private final double snowVolume;

    public WeatherData(double temp, String condition,
                       double rainVolume, double snowVolume) {
        this.temp = temp;
        this.condition = condition.toLowerCase();
        this.rainVolume = rainVolume;
        this.snowVolume = snowVolume;
    }

    public double getTemp() {
        return temp;
    }

    public String getCondition() {
        return condition;
    }

    public double getRainVolume() {
        return rainVolume;
    }

    public double getSnowVolume() {
        return snowVolume;
    }
}
