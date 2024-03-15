package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.Intake.*;

public class IntakeCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Intake m_subsytem;
    private final DigitalInput input;
    private IntakeState intakePosition;

    // @param subsystem
    public IntakeCommand(Intake subsystem) {
        m_subsytem = subsystem;
        input = m_subsytem.getInput();
        intakePosition = IntakeState.Deactivated;

        addRequirements(subsystem);
    }


    @Override
    public void initialize() {
        m_subsytem.runIntake();
    }

    @Override 
    public void end(boolean interrupted) {
        if (input.get() && intakePosition == IntakeState.Activated)
        {
            m_subsytem.reverseIntake();
            intakePosition = IntakeState.Deactivated;
        }
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}
