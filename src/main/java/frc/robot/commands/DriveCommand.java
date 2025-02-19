package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;

public class DriveCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final Joystick joystick;

    public DriveCommand(DriveSubsystem subsystem, Joystick joystick) {
        this.driveSubsystem = subsystem;
        this.joystick = joystick;
        addRequirements(driveSubsystem); // Ensures this command has control over the drive subsystem
    }

    @Override
    public void execute() {
        double forward = -joystick.getRawAxis(1); // Y-axis for forward/backward
        double rotation = joystick.getRawAxis(4); // X-axis for turning

        driveSubsystem.arcadeDrive(forward, rotation);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop(); // Stop the motors when the command ends
    }

    @Override
    public boolean isFinished() {
        return false; // Runs continuously until interrupted
    }
}
