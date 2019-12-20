package ashypilo.Exception;

public class SimulationRunException extends Exception {

    public SimulationRunException() {

    }

    public void printError() {
        System.out.println("Is numbers to run the simulation is not true");
    }
}
