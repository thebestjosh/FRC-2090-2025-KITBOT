package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {
    private final DriveControls controls = new DriveControls();
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();

    public RobotContainer() {
        driveSubsystem.setDefaultCommand(new DriveCommand(driveSubsystem, controls));
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        // controls.zeroGyro.onTrue(() -> System.out.println("Gyro Reset!")); // Placeholder gyro reset
    }

    public Command getAutonomousCommand() {
        return new DriveCommand(driveSubsystem, controls).withTimeout(2); // 2-sec drive forward
    }
}
