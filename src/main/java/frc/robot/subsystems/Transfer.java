package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import static frc.robot.Constants.Transfer.*;

public class Transfer extends SubsystemBase {
    private final TalonSRX transferController;
    //private final DigitalInput transferBreakbeam;
    //public final Trigger noteInTransfer = new Trigger(this::getBreakbeam);


    public Transfer() {
        transferController = new TalonSRX(transferMotorID); 
        //transferBreakbeam = new DigitalInput(3);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Transfer Speed", transferController.getMotorOutputPercent());
        //SmartDashboard.putBoolean("Transfer Full", transferBreakbeam.get());
    }

    public void runTransfer() {
        transferController.set(ControlMode.PercentOutput, maxSpeed);
    }

    public void reverseTransfer() {
        transferController.set(ControlMode.PercentOutput, -maxSpeed);
    }

    public void stopTransfer() {
        transferController.set(ControlMode.PercentOutput, 0);   
    }

    // public boolean getBreakbeam() {
    //     return transferBreakbeam.get();
    // }
}