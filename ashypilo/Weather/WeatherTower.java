package ashypilo.Weather;

import ashypilo.Coordinates.Coordinates;
import ashypilo.Tower.Tower;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        super.conditionsChanged();
    }
}
