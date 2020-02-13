package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.IntakeState;

public class IntakeSubsystem extends SubsystemBase {
    private Compressor compressor;
    private DoubleSolenoid intakePiston;
    private SpeedController intakeMotor;
    private IntakeState state;

    public IntakeSubsystem() {
        state = IntakeState.RETRACTED;
        intakeMotor = new Talon(6);
        compressor = new Compressor(0);
        intakePiston = new DoubleSolenoid(2,4);
        compressor.setClosedLoopControl(true);
    }
    
    public void setState(IntakeState state){
        this.state = state;

    }

    public void periodic() {
        //To do: Only momentary set piston
        switch(state){
            case RETRACTED: 
                intakeMotor.set(0);
                intakePiston.set(Value.kReverse);
                return;
            case EXTENDED_INTAKING:
                intakeMotor.set(1);
                intakePiston.set(Value.kForward);
                return;
            case EXTENDED_STOPPED:
                intakeMotor.set(0);
                intakePiston.set(Value.kForward);
                return;
        }
    }
    
}