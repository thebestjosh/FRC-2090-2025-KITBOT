package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    private final PWMSparkMax leftMotor1 = new PWMSparkMax(15);
    private final PWMSparkMax leftMotor2 = new PWMSparkMax(14);
    private final PWMSparkMax rightMotor1 = new PWMSparkMax(13);
    private final PWMSparkMax rightMotor2 = new PWMSparkMax(12);

    private final MotorControllerGroup leftMotors = new MotorControllerGroup(leftMotor1, leftMotor2);
    private final MotorControllerGroup rightMotors = new MotorControllerGroup(rightMotor1, rightMotor2);

    private final DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

    public DriveSubsystem() {
        // Adjust motor inversion based on testing
        leftMotors.setInverted(true);  // If motors run in opposite directions, invert as needed
        rightMotors.setInverted(false);
    }

    public void arcadeDrive(double forward, double rotation) {
        drive.arcadeDrive(forward, rotation);
    }

    public void stop() {
        drive.stopMotor();
    }
}
