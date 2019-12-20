package ashypilo.Flayable;

import ashypilo.Weather.WeatherTower;

public interface Flyable {
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
}
