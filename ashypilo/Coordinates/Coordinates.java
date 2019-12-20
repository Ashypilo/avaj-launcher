package ashypilo.Coordinates;

public class Coordinates {

    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        if (height < 100 && height > 0)
            this.height = height;
        else if (height > 100)
            this.height = 100;
        else
            this.height = 0;
        if (latitude < 0)
            latitude = 0;
        if (longitude < 0)
            longitude = 0;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getHeight() {
        return this.height;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getLongitude() {
        return this.longitude;
    }
}
