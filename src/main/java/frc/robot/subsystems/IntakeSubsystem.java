package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase{
    private Compressor compressor;
    public DoubleSolenoid intakePiston;
    public final WPI_TalonSRX intakeMotor;
    public final AnalogInput ballSensor;

    public IntakeSubsystem() {
        intakeMotor = new WPI_TalonSRX(Constants.intakeMotorCANID);
        compressor = new Compressor(10); 
        ballSensor = new AnalogInput(1);
        intakePiston = new DoubleSolenoid(Constants.intakePistonPort1,Constants.intakePistonPort2);
        
       
        SendableRegistry.setName(ballSensor, "BallSensor");
        SendableRegistry.setName(intakePiston, "intakePiston1");
        SendableRegistry.setName(compressor, "compressor");
        SendableRegistry.setName(intakeMotor, "intakeMotor");
    }

    public void periodic() {
        compressor.setClosedLoopControl(true);
    }
    
}