package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class DriveControls {
    private final Joystick joystick = new Joystick(0);

    public final JoystickButton zeroGyro = new JoystickButton(joystick, 3);

    public double getForward() { return -joystick.getY(); } // Invert Y for correct movement
    public double getRotation() { return joystick.getX(); } 
}
