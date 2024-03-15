package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.util.Color;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Shooter.*;

public class Shooter extends SubsystemBase {
    private final TalonSRX shooterControllerL;
    private final TalonSRX shooterControllerR;
    private double velocity;
    private double spunUpVelocity = 999;

    public Shooter() {
        shooterControllerL = new TalonSRX(leftMotorID); 
        shooterControllerR = new TalonSRX(rightMotorID); 
    }

    @Override
    public void periodic(){
        velocity = shooterControllerL.getActiveTrajectoryVelocity();
        SmartDashboard.putNumber("Shooter Speed", maxSpeed);
        SmartDashboard.putNumber("Shooter Velocity", shooterControllerL.getActiveTrajectoryVelocity());
        checkGamerLight();
    }


    public void runShooter() {
        shooterControllerL.set(ControlMode.PercentOutput, maxSpeed);
        shooterControllerR.set(ControlMode.PercentOutput, maxSpeed);
    }


    public void stopShooter() {
        shooterControllerL.set(ControlMode.PercentOutput, 0);
        shooterControllerR.set(ControlMode.PercentOutput, 0);
    }

    // pretty sure this does the same thing as shooterSpunUp below
    public void checkGamerLight() {
        // if (velocity>300) {
        //     blinkin.set(0.77);
        // }
    }

    // checks if shooter is ready; pretty sure this is the same as checkGamerLight
    public boolean shooterSpunUp() {
        if (velocity >= spunUpVelocity)
            return true;

        return false;
    }

    public double getVelocity() {
        return velocity;
    }
}

