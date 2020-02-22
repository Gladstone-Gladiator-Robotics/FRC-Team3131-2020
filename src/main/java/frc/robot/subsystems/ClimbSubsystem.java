/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
  private final WPI_TalonSRX climbMotor;
  private final DigitalOutput climbLock;
  private boolean running = false;
  public ClimbSubsystem() {
    climbMotor = new WPI_TalonSRX(9);
    climbLock = new DigitalOutput(8);
    climbLock.set(true);
    SendableRegistry.setName(climbLock, "climbLock");
    SendableRegistry.setName(climbMotor, "climbMotor");
  }

  public void unlock(){
    climbLock.set(false);
  }

  public void climb(){
    running = true;
  }
  public void stop(){
    running = false;
  }

  @Override
  public void periodic() {
    if(running == true){
      climbMotor.set(1);
    } else{
      climbMotor.set(0);
    }
  }
}
