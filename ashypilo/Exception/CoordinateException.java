package ashypilo.Exception;

public class CoordinateException extends Exception {
    private String name;
    public CoordinateException(String name, String type) {
        this.name = type + "#" + name;
    }

    public void printError() {
        System.out.println(name + " coordinate is not true");
    }
}
