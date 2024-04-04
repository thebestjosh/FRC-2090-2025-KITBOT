package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import static frc.robot.Constants.Shooter.*;

public class Shooter extends SubsystemBase {
    private final TalonSRX shooterControllerL;
    private final TalonSRX shooterControllerR;
    private double velocityL;
    private double velocityR;
    private double spunUpVelocity = speakerSpunUpVelocity;
    public final Trigger shooterIsSpunUp = new Trigger(this::shooterIsSpunUp);

    public Shooter() {
        shooterControllerL = new TalonSRX(leftMotorID); 
        shooterControllerR = new TalonSRX(rightMotorID); 
    }

    @Override
    public void periodic() {
        //getSelectedSensorVelocity returns ticks per 100ms, so we can just convert to rpm w/ dimensional analysis
        velocityL = shooterControllerL.getSelectedSensorVelocity() / 4096 / 10 * 60;
        velocityR = shooterControllerR.getSelectedSensorVelocity() / 4096 / 10 * 60;
        SmartDashboard.putNumber("Shooter Velocity (Left)", velocityL);
        SmartDashboard.putNumber("Shooter Velocity (Right)", velocityR);
    }


    public void runShooter() {
        shooterControllerL.set(ControlMode.PercentOutput, -maxSpeed);
        shooterControllerR.set(ControlMode.PercentOutput, maxSpeed);
        spunUpVelocity = maxSpunUpVelocity;
    }

    public void runShooterEject() {
        shooterControllerL.set(ControlMode.PercentOutput, -ejectSpeed);
        shooterControllerR.set(ControlMode.PercentOutput, ejectSpeed);
    }

    public void runShooterAmp() {
        shooterControllerL.set(ControlMode.PercentOutput, -ampSpeed);
        shooterControllerR.set(ControlMode.PercentOutput, ampSpeed);
        spunUpVelocity = ampSpunUpVelocity;
    }

    public void runShooterSpeaker() {
        shooterControllerL.set(ControlMode.PercentOutput, -speakerSpeed);
        shooterControllerR.set(ControlMode.PercentOutput, speakerSpeed);
        spunUpVelocity = speakerSpunUpVelocity;
    }

    public void stopShooter() {
        shooterControllerL.set(ControlMode.PercentOutput, 0);
        shooterControllerR.set(ControlMode.PercentOutput, 0);
    }

    public void reverseShooter() {
        shooterControllerL.set(ControlMode.PercentOutput, maxSpeed);
        shooterControllerR.set(ControlMode.PercentOutput, -maxSpeed);
    }

    // checks if shooter is ready
    public boolean shooterIsSpunUp() {
        if (velocityL >= spunUpVelocity)
            return true;

        return false;
    }

    public double getVelocity() {
        return velocityL;
    }

    /**
     * The new objective of this method is to just move the velocity up and down
     * with the dial.
     * 
     * @param oldSpeed the current set speed
     * @param adjuster the adjustment value
     * @return the new speed
     */
    public double adjustSpeed(double oldSpeed, double adjuster)
    {
        double workingArea;
        adjuster = MathUtil.applyDeadband(-adjuster, adjustmentDeadband);
        workingArea = adjuster > 0 ? maxSpeed - oldSpeed : oldSpeed;
        return oldSpeed + workingArea*adjuster;
    }
}