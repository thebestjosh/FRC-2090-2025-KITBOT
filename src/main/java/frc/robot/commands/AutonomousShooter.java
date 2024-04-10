// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class AutonomousShooter extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_subsystem;
  private final Transfer t_subsystem;
  private double timestamp;
  private boolean finished = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutonomousShooter(Shooter shooter, Transfer transfer) {
    m_subsystem = shooter;
    t_subsystem = transfer;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter, transfer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timestamp = Timer.getFPGATimestamp();
    m_subsystem.runShooterSpeaker();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_subsystem.shooterIsSpunUp())
    {
      t_subsystem.runTransfer();
    }
    if (Timer.getFPGATimestamp() - timestamp > 5) {
      finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stopShooter();
    t_subsystem.stopTransfer();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
