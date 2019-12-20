package ashypilo.Exception;

public class WrongFormatException  extends Exception {
    public WrongFormatException() {

    }

    public void printError() {
        System.out.println("Wrong format simulation");
    }
}
