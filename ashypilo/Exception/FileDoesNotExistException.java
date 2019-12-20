package ashypilo.Exception;

public class FileDoesNotExistException extends Exception {

    public FileDoesNotExistException() {
    }

    public void printError() {
        System.out.println("File is does not exist");
    }
}
