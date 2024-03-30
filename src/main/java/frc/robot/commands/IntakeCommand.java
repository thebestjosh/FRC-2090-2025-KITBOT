package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.Intake.*;

public class IntakeCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Intake m_subsytem;
    private boolean input;
    private boolean followingRing;
    private IntakeState intakePosition;

    // @param subsystem
    public IntakeCommand(Intake subsystem) {
        m_subsytem = subsystem;
        input = m_subsytem.getBreakbeam();
        intakePosition = IntakeState.Deactivated;
        followingRing = false;

        addRequirements(subsystem);
    }


    @Override
    public void initialize() {}

    @Override
    public void execute() {
        input = m_subsytem.getBreakbeam();

        if (m_subsytem.intakeNoLimits)
            followingRing = false;
        else if (input)
            followingRing = true;

        // if the intake is deactivated, extend
        if (intakePosition == IntakeState.Deactivated) {
            m_subsytem.engageIntake();
            intakePosition = IntakeState.Activated;
        }
        // If the intake is empty and extened but it previously had a ring in it, that means the ring passed through to the transfer, so retract
        // (this is written b/c we don't have a sensor in the transfer to be sure it's in the transfer)
        else if (!input && intakePosition == IntakeState.Activated && followingRing) {
            m_subsytem.disengageIntake();
            intakePosition = IntakeState.Deactivated;
            followingRing = false;
        }
    }

    @Override 
    public void end(boolean interrupted) {
        if (intakePosition == IntakeState.Activated)
        {
            m_subsytem.disengageIntake();
            intakePosition = IntakeState.Deactivated;
        }
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}