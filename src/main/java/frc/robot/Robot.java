package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    private RobotContainer robotContainer;

    @Override
    public void robotInit() {
        // Initializes the robotContainer which sets up subsystems, commands, and controls.
        robotContainer = new RobotContainer();
    }

    @Override
    public void teleopPeriodic() {
        // Executes all scheduled commands during teleop.
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        // Cancels all commands when disabled to prevent unintended behavior.
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void disabledPeriodic() {
        // Allows any necessary periodic updates in the disabled state.
        CommandScheduler.getInstance().run();
    }
}
