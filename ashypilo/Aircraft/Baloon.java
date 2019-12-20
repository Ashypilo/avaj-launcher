package ashypilo.Aircraft;

import ashypilo.Coordinates.Coordinates;
import ashypilo.Exception.LandingException;
import ashypilo.Flayable.Flyable;
import ashypilo.Simulator;
import ashypilo.Weather.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {

        super(name, coordinates);
    }

    public void updateConditions() {
        try {
            if (super.coordinates.getHeight() == 0 || super.coordinates.getLatitude() == 0 || super.coordinates.getLongitude() == 0)  {
                this.weatherTower.unregister(this);
                String landing = "Baloon#" + super.name + "(" + super.id + ")";
                throw new LandingException(landing);
            }
            String weater = weatherTower.getWeather(super.coordinates);
            switch (weater) {
                case "SUN":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude() + 2, super.coordinates.getLatitude(), super.coordinates.getHeight() + 4);
                    Simulator.writeFile("Baloon#" + super.name + "(" + super.id + "): Unbowed, Unbent, Unbroken.");
                    break;
                case "RAIN":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude(), super.coordinates.getLatitude(), super.coordinates.getHeight() - 5);
                    Simulator.writeFile("Baloon#" + super.name + "(" + super.id + "): Rain, cry for the Rains of Castamere.");
                    break;
                case "FOG":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude(), super.coordinates.getLatitude(), super.coordinates.getHeight() - 3);
                    Simulator.writeFile("Baloon#" + super.name + "(" + super.id + "): Fog, Others are coming.");
                    break;
                case "SNOW":
                    super.coordinates = new Coordinates(super.coordinates.getLongitude(), super.coordinates.getLatitude(), super.coordinates.getHeight() - 15);
                    Simulator.writeFile("Baloon#" + super.name + "(" + super.id + "): Starks are right, Winter is coming.");
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
        Simulator.writeFile("Tower say: Baloon#" + super.name + "(" + super.id + ") registered to weather tower.");
    }

}