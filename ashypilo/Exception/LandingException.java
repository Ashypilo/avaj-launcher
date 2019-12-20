package ashypilo.Exception;

import ashypilo.Simulator;

public class LandingException extends Exception {
    String name;
    public LandingException(String name) {
        this.name = name;
    }

    public void printError() {
        Simulator.writeFile(name + " landing");
        Simulator.writeFile("Tower say: " + name + " unregistered from weather tower.");
    }
}
