
//Manual: https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf 

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class GamerLights extends SubsystemBase {

    private Spark blinkin = new Spark(0); // PWM port of the driver
    private double lastToggleTime;
    private boolean isBlue;
    private double currentColor;

    public GamerLights() {
        lastToggleTime = Timer.getFPGATimestamp(); // Get initial timestamp
        isBlue = true; // Start with blue
        currentColor = 0.87; // Initialize to blue color
        blinkin.set(currentColor); // Set initial color
    }

    // Update the color based on the current state
    private void updateColor() {
        if (isBlue) {
            currentColor = 0.87; // Blue color value
        } else {
            currentColor = 0.69; // Yellow color value
        }
        blinkin.set(currentColor); // Set the color
    }

    // Method to toggle the color
    public void toggleColor() {
        isBlue = !isBlue; // Toggle the color state
        updateColor(); // Update the color
    }

    // Method to "start" the timer
    public void startTimer() {
        lastToggleTime = Timer.getFPGATimestamp(); // Reset the timestamp
    }

    public void periodic() {
     
        // check if it's time to toggle the color and reset the timer if needed
        if (Timer.getFPGATimestamp() - lastToggleTime >= 2 && this.getCurrentCommand() == null) { // Check if 2 seconds have elapsed and nothing else is currently using the lights
            toggleColor(); // Toggle the color
            startTimer(); // Reset the timer
        }
    }


    // Method to set the button light color (strobe white)
    public void ampLight() {
        blinkin.set(-0.05); // strobe white color
    }

    // Method to set the button light color (strobe gold)
    public void coopertitionLight() {
         blinkin.set(-0.07); // strobe gold color
    }

    // Method to set the light color (solid green)
    public void readyToShootLight() {
        blinkin.set(0.77); // solid green color
    }

    // Method to set light solid dark green (probably should change this)
    public void shootingDistanceLight() { blinkin.set(0.75); }

    // Method to set light solid red
    public void noteInIntakeLight() { blinkin.set(0.61); }

    // Method to set light solid orange
    public void noteInTransferLight() { blinkin.set(0.65); }

}