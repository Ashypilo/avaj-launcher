package ashypilo.Exception;

public class UnknownTypeException extends Exception {
    public UnknownTypeException() {

    }

    public void printError() {
        System.out.println("Unknown type or name");
    }
}
