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
    public DoubleSolenoid intakePiston1;
    public DoubleSolenoid intakePiston2;
    public final WPI_TalonSRX intakeMotor;
    public final AnalogInput ballSensor;

    public IntakeSubsystem() {
        intakeMotor = new WPI_TalonSRX(Constants.intakeMotorCANID);
        compressor = new Compressor(0); 
        ballSensor = new AnalogInput(1);
        intakePiston1 = new DoubleSolenoid(Constants.intakePiston1Port1,Constants.intakePiston1Port2);
        intakePiston2 = new DoubleSolenoid(Constants.intakePiston2Port1,Constants.intakePiston2Port2);
        compressor.setClosedLoopControl(true);
       
        SendableRegistry.setName(ballSensor, "BallSensor");
        SendableRegistry.setName(intakePiston1, "intakePiston1");
        SendableRegistry.setName(intakePiston2, "intakePiston2");
        SendableRegistry.setName(compressor, "compressor");
        SendableRegistry.setName(intakeMotor, "intakeMotor");
    }

    public void periodic() {

    }
    
}