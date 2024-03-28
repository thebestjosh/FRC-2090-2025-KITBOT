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
    private IntakeState intakePosition;

    // @param subsystem
    public IntakeCommand(Intake subsystem) {
        m_subsytem = subsystem;
        input = m_subsytem.getDigitalInput();
        intakePosition = IntakeState.Deactivated;

        addRequirements(subsystem);
    }


    @Override
    public void initialize() {}

    @Override
    public void execute() {
        input = m_subsytem.getDigitalInput();

        if (!input && intakePosition == IntakeState.Deactivated) {
            m_subsytem.engageIntake();
            intakePosition = IntakeState.Activated;
        }
        else if (input && intakePosition == IntakeState.Activated) {
            m_subsytem.disengageIntake();
            intakePosition = IntakeState.Deactivated;
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
