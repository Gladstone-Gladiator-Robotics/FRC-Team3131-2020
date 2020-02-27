package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{
    private Compressor compressor;
    public DoubleSolenoid intakePiston;
    public final WPI_TalonSRX intakeMotor;

    public IntakeSubsystem() {
        intakeMotor = new WPI_TalonSRX(6);
        compressor = new Compressor(0);
        intakePiston = new DoubleSolenoid(2,4);
        compressor.setClosedLoopControl(true);
        SendableRegistry.setName(intakePiston, "intakePiston");
        SendableRegistry.setName(compressor, "compressor");
        SendableRegistry.setName(intakeMotor, "IntakeMotor");
    }

    public void periodic() {

    }
    
}