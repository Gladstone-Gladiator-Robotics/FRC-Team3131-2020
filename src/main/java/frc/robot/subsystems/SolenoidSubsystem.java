package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SolenoidSubsystem extends SubsystemBase {
    public DoubleSolenoid intakePiston;
    public Compressor compressor;

    public SolenoidSubsystem() {
        compressor = new Compressor(0);
        intakePiston = new DoubleSolenoid(2,4);
        compressor.setClosedLoopControl(true);
    }

    public void periodic() {
    }
}