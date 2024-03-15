// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import frc.robot.Constants.Intake.*;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transfer;
import edu.wpi.first.wpilibj2.command.Command;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalInput;

/** An example command that uses an example subsystem. */
public class MoveToTransfer extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Transfer m_subsystem;
    private final Intake i_subsystem;
    private final DigitalInput transfer_input;
    private final DigitalInput intake_input;
    private IntakeState intakePosition;


    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public MoveToTransfer(Transfer transfer, Intake intake) {
        m_subsystem = transfer;
        i_subsystem = intake;
        intakePosition = IntakeState.Deactivated;
        transfer_input = m_subsystem.getDigitalInput();
        intake_input = i_subsystem.getDigitalInput();
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_subsystem, i_subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //m_subsystem.runTransfer();

        if (transfer_input.get() && intakePosition == IntakeState.Deactivated) {
            i_subsystem.moveToTransfer();
            m_subsystem.runTransfer();
        };
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        i_subsystem.stopMoveToTransfer();
        m_subsystem.stopTransfer();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
