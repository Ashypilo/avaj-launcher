package ashypilo.Aircraft;

import ashypilo.Coordinates.Coordinates;
import ashypilo.Exception.LandingException;
import ashypilo.Flayable.Flyable;
import ashypilo.Simulator;
import ashypilo.Weather.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {

        super(name, coordinates);
    }

    public void updateConditions() {
        try {
            if (super.coordinates.getHeight() == 0 || super.coordinates.getLatitude() == 0 || super.coordinates.getLongitude() == 0) {
                this.weatherTower.unregister(this);
                String landing = "JetPlane#" + super.name + "(" + super.id + ")";
                throw new LandingException(landing);
            }
            String weater = weatherTower.getWeather(super.coordinates);
            switch (weater) {
                case "SUN":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude(), super.coordinates.getLatitude() + 10, super.coordinates.getHeight() + 2);
                    Simulator.writeFile("JetPlane#" + super.name + "(" + super.id + "): The sun, what a beautiful sun.");
                    break;
                case "RAIN":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude(), super.coordinates.getLatitude() + 5, super.coordinates.getHeight());
                    Simulator.writeFile("JetPlane#" + super.name + "(" + super.id + "): Rain, rain again.");
                    break;
                case "FOG":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude(), super.coordinates.getLatitude() + 1, super.coordinates.getHeight());
                    Simulator.writeFile("JetPlane#" + super.name + "(" + super.id + "): Fog, nothing is visible because of it.");
                    break;
                case "SNOW":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude(), super.coordinates.getLatitude(), super.coordinates.getHeight() - 7);
                    Simulator.writeFile("JetPlane#" + super.name + "(" + super.id + "): You know nothing, Jon Snow.");
                    break;
            }
        }
        catch (LandingException e){
            e.printError();
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writeFile("Tower say: JetPlane#" + super.name + "(" + super.id + ") registered to weather tower.");
    }

}
