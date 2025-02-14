package frc.robot;

// import com.pathplanner.lib.auto.AutoBuilder;
// import com.pathplanner.lib.commands.PathPlannerAuto;
// import com.pathplanner.lib.path.PathPlannerPath;
import frc.robot.commands.TeleopSwerve;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Swerve;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final DriveControls controls = new DriveControls();

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> controls.getForward() / 1.5, 
                () -> controls.getStrafe() / 1.5, 
                () -> controls.getRotation() / 1.5, 
                () -> false // Removed robot-centric control
            )
        );
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. 
     */
    private void configureButtonBindings() {
        /* Drive Speed Modes */
        controls.slowMode.whileTrue(
            new TeleopSwerve(s_Swerve, 
                () -> controls.getForward() / 3, 
                () -> controls.getStrafe() / 3, 
                () -> controls.getRotation() / 3, 
                () -> false
            )
        );
        controls.fastMode.whileTrue(
            new TeleopSwerve(s_Swerve, 
                () -> controls.getForward(), 
                () -> controls.getStrafe(), 
                () -> controls.getRotation(), 
                () -> false
            )
        );

        /* Gyro Reset */
        controls.zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
    }
}

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
//     public Command getAutoRotateCommand() {
//         PathPlannerPath path = PathPlannerPath.fromChoreoTrajectory("Rotate");
//         return AutoBuilder.followPath(path);
//     }

//     public Command getAutoDriveCommand() {
//         PathPlannerPath path = PathPlannerPath.fromChoreoTrajectory("Drive & Rotate");
//         return AutoBuilder.followPath(path);
//     }

//     public Command getAutoTestCommand() {
//         return new PathPlannerAuto("New Auto");
//     }

//     public Command autoCommandL() {
//         return new PathPlannerAuto("autoL");
//     }

//     public Command autoCommandC() {
//         return new PathPlannerAuto("autoC");
//     }

//     public Command autoCommandR() {
//         return new PathPlannerAuto("autoR");
//     }

//     public Command getForwardCommand() {
//         return new PathPlannerAuto("ForwardAuto");
//     }
// }
