package ashypilo.Exception;

public class HeightException extends Exception {
    private String name;
    public HeightException(String name, String type) {
        this.name = type + "#" + name;
    }

    public void printError() {
        System.out.println(name + " height is not true");
    }
}
