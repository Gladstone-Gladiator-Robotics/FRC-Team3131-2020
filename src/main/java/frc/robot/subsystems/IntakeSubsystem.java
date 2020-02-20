package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private Compressor compressor;
    public DoubleSolenoid intakePiston;
    private SpeedController intakeMotor;

    public IntakeSubsystem() {
        intakeMotor = new Talon(6);
        compressor = new Compressor(0);
        intakePiston = new DoubleSolenoid(2,4);
        compressor.setClosedLoopControl(true);
    }

    public void periodic() {

    }
    
}