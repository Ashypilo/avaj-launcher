package ashypilo.Aircraft;

import ashypilo.Coordinates.Coordinates;
import ashypilo.Exception.LandingException;
import ashypilo.Flayable.Flyable;
import ashypilo.Simulator;
import ashypilo.Weather.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);

    }

    public void updateConditions() {
        try {
            if (super.coordinates.getHeight() == 0 || super.coordinates.getLatitude() == 0 || super.coordinates.getLongitude() == 0)  {
                this.weatherTower.unregister(this);
                String landing = "Helicopter#" + super.name + "(" + super.id + ")";
                throw new LandingException(landing);
            }
            String weater = weatherTower.getWeather(super.coordinates);
            switch (weater) {
                case "SUN":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude() + 10, super.coordinates.getLatitude(), super.coordinates.getHeight() + 2);
                    Simulator.writeFile("Helicopter#" + super.name + "(" + super.id + "): Let's enjoy the good weather and take some pics.");
                    break;
                case "RAIN":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude() + 5, super.coordinates.getLatitude(), super.coordinates.getHeight());
                    Simulator.writeFile("Helicopter#" + super.name + "(" + super.id + "): It's raining. Better watch out for lightings.");
                    break;
                case "FOG":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude() + 1, super.coordinates.getLatitude(), super.coordinates.getHeight());
                    Simulator.writeFile("Helicopter#" + super.name + "(" + super.id + "): This is hot.");
                    break;
                case "SNOW":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude(), super.coordinates.getLatitude(), super.coordinates.getHeight() - 12);
                    Simulator.writeFile("Helicopter#" + super.name + "(" + super.id + "): My rotor is going to freeze!");
                    break;
            }
        }
        catch (LandingException e) {
            e.printError();
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writeFile("Tower say: Helicopter#" + super.name + "(" + super.id + ") registered to weather tower.");
    }

}
