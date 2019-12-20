package ashypilo.Aircraft;

import ashypilo.Coordinates.Coordinates;
import ashypilo.Flayable.Flyable;

public class AircraftFactory {

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

        Coordinates cord = new Coordinates(longitude, latitude, height);
        if (type.equals("Helicopter") && name.charAt(0) == 'H') {
            return new Helicopter(name, cord);
        }
        else if (type.equals("JetPlane") && name.charAt(0) == 'J') {
            return new JetPlane(name, cord);
        }
        else if (type.equals("Baloon") && name.charAt(0) == 'B') {
            return new Baloon(name, cord);
        }
        return null;
    }
}
