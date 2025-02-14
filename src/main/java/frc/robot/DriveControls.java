package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class handles joystick inputs for controlling the drivetrain.
 */
public class DriveControls {
    /* Controllers */
    private final Joystick joystick0 = new Joystick(0); // Left joystick for movement
    private final Joystick joystick1 = new Joystick(1); // Middle joystick for rotation

    /* Drive Speed Modes */
    public final JoystickButton slowMode = new JoystickButton(joystick1, 1); 
    public final JoystickButton fastMode = new JoystickButton(joystick1, 2); 

    /* Gyro Reset Button */
    public final JoystickButton zeroGyro = new JoystickButton(joystick1, 3);

    public double getForward() { return -joystick0.getY(); } // Inverted Y-axis for correct movement
    public double getStrafe() { return joystick0.getX(); } 
    public double getRotation() { return joystick1.getX(); }  
}
