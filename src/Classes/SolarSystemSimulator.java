package Classes;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Simulates a solar system with the Sun and multiple planets orbiting it.
 * The simulation includes animated planets and uses a graphical interface to
 * visualize their movements in space.
 */
public class SolarSystemSimulator {

    private JFrame frame;
    private JPanel panel;
    private Image backgroundImage;
    private Sun sun;
    private Earth earth;
    private Saturn saturn;
    private Mercury mercury;

    // Constants
    private static final int FRAME_WIDTH = 1000; // Frame width
    private static final int FRAME_HEIGHT = 1000; // Frame height
    private static final String PANEL_BACKGROUND_PATH = "./src/resources/SpaceBackground.png";

    /**
     * Runs the solar system simulator, initializing the GUI, planets, and animation loop.
     */
    public void runSimulator() {
        // Set up the frame
        frame = new JFrame("Solar System Simulator");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the background image
        backgroundImage = new ImageIcon(PANEL_BACKGROUND_PATH).getImage();

        // Initialize the Sun and planets
        setup();

        // Create the panel for rendering
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawComponents(g);
            }
        };

        // Add the panel to the frame
        frame.add(panel);

        // Timer for animation (60 FPS)
        Timer timer = new Timer(16, e -> {
            updateSimulation(); // Update planetary positions
            panel.repaint(); // Repaint the panel
        });
        timer.start();

        // Make the frame visible
        frame.setVisible(true);
    }

    /**
     * Initializes the Sun and planets with their properties.
     */
    private void setup() {
        try {
            // Set up the Sun
            int[] sunCoordinates = {FRAME_WIDTH / 2 - 100, FRAME_HEIGHT / 2 - 100}; // Centered Sun
            double scaledSunMass = 1.989; // Scaled mass (10^27 kg)
            double scaledSunRadius = 6.96; // Scaled radius (10^5 km)
            sun = new Sun(scaledSunMass, scaledSunRadius, sunCoordinates);

            // Set up Earth
            double[] earthInitialPosition = {sunCoordinates[0] + 300, sunCoordinates[1] + 100};
            double[] earthInitialVelocity = {0, 850}; // Perpendicular to the Sun
            earth = new Earth(0.005972, 0.0637, earthInitialPosition, earthInitialVelocity);

            // Set up Mercury
            double[] mercuryInitialPosition = {sunCoordinates[0] + 250, sunCoordinates[1] + 100};
            double[] mercuryInitialVelocity = {0, 850};
            mercury = new Mercury(0.0003285, 0.00244, mercuryInitialPosition, mercuryInitialVelocity);

            // Set up Saturn
            double[] saturnInitialPosition = {sunCoordinates[0] + 380, sunCoordinates[1] + 100};
            double[] saturnInitialVelocity = {0, 750};
            saturn = new Saturn(0.5683, 0.5825, saturnInitialPosition, saturnInitialVelocity);

        } catch (Exception e) {
            System.out.println("Error setting up simulation: " + e.getMessage());
        }
    }

    /**
     * Updates the simulation by recalculating the positions of the planets.
     */
    private void updateSimulation() {
        double[] sunPosition = {sun.getCoordinates()[0] + 100, sun.getCoordinates()[1] + 100}; // Sun center
        double timeStep = 0.016; // 16 ms per frame
        earth.updatePosition(sunPosition, sun.getMass(), timeStep);
        mercury.updatePosition(sunPosition, sun.getMass(), timeStep);
        saturn.updatePosition(sunPosition, sun.getMass(), timeStep);
    }

    /**
     * Draws the Sun, planets, and background image on the panel.
     * 
     * @param g the Graphics object used to draw components.
     */
    private void drawComponents(Graphics g) {
        // Draw the background
        g.drawImage(backgroundImage, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);

        try {
        	 // Draw the Sun
            g.drawImage(
                new ImageIcon(sun.getImage()).getImage(),
                sun.getCoordinates()[0],
                sun.getCoordinates()[1],
                200, // Width
                200, // Height
                null
            );
		} catch (Exception e) {
			 System.out.println("Error setting up simulation: " + e.getMessage());
		}
       
        try {
        	// Draw Earth
            g.drawImage(
                new ImageIcon(earth.getImage()).getImage(),
                earth.getCoordinates()[0] - 20,
                earth.getCoordinates()[1] - 20,
                40, // Width
                40, // Height
                null
            );

            // Draw Mercury
            g.drawImage(
                new ImageIcon(mercury.getImage()).getImage(),
                mercury.getCoordinates()[0] - 5,
                mercury.getCoordinates()[1] - 5,
                10, // Width
                10, // Height
                null
            );

            // Draw Saturn
            g.drawImage(
                new ImageIcon(saturn.getImage()).getImage(),
                saturn.getCoordinates()[0] - 32,
                saturn.getCoordinates()[1] - 25,
                65, // Width
                50, // Height
                null
            );
		} catch (Exception e) {
			System.out.println("Error setting up simulation: " + e.getMessage());
		}
        
    }
}
