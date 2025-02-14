package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    private final PWMSparkMax leftMotor = new PWMSparkMax(0);  // Change ports as needed
    private final PWMSparkMax rightMotor = new PWMSparkMax(1); // Change ports as needed

    private final DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);

    public DriveSubsystem() {
        rightMotor.setInverted(true); // Ensures correct direction
    }

    public void arcadeDrive(double forward, double rotation) {
        drive.arcadeDrive(forward, rotation);
    }

    public void stop() {
        drive.stopMotor();
    }
}
