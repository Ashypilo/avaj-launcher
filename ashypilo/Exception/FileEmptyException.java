package ashypilo.Exception;

import java.io.IOException;
import java.lang.Exception;

public class FileEmptyException extends Exception {
    public FileEmptyException() {
    }

    public void printError() {
        System.out.println("File is empty");
    }
}
