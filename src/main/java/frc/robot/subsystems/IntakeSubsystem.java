package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private Compressor compressor;
    public DoubleSolenoid intakePiston;
    public final Talon intakeMotor;

    public IntakeSubsystem() {
        intakeMotor = new Talon(6);
        compressor = new Compressor(0);
        intakePiston = new DoubleSolenoid(2,4);
        compressor.setClosedLoopControl(true);
        SendableRegistry.setName(intakePiston, "intakePiston");
        SendableRegistry.setName(compressor, "compressor");
    }

    public void periodic() {

    }
    
}