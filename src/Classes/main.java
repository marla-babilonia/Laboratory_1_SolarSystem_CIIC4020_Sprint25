package Classes;

/**
 * Entry point for the Solar System Simulator application.
 * This class initializes and runs the simulator.
 */
public class main {

    /**
     * Main method to start the Solar System Simulator.
     * 
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        SolarSystemSimulator simulator = new SolarSystemSimulator();
        simulator.runSimulator();
    }
}
