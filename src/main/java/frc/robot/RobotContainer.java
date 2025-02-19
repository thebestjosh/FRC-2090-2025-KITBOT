package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.DriveCommand;

public class RobotContainer {
    private final Joystick driverJoystick = new Joystick(0);
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();

    public RobotContainer() {
        driveSubsystem.setDefaultCommand(new DriveCommand(driveSubsystem, driverJoystick));
    }
}
