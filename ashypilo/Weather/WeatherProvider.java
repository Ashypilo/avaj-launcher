package ashypilo.Weather;

import ashypilo.Coordinates.Coordinates;
import java.util.Random;

public class WeatherProvider {

  private static WeatherProvider weatherProvider = new WeatherProvider();
  private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

  private WeatherProvider() {

  }

  public static WeatherProvider getProvider() {
    return weatherProvider;
  }

  public String getCurrentWeather(Coordinates coordinates) {

      Random random = new Random();
      int num = random.nextInt(weather.length);
      return weather[num];
  }
}
