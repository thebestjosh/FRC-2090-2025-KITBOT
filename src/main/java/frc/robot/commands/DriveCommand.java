package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.DriveControls;

public class DriveCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final DriveControls controls;

    public DriveCommand(DriveSubsystem driveSubsystem, DriveControls controls) {
        this.driveSubsystem = driveSubsystem;
        this.controls = controls;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.arcadeDrive(controls.getForward(), controls.getRotation());
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }
}
