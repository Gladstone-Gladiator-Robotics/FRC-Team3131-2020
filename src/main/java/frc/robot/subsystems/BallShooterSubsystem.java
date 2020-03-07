/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;


/**
 * Add your docs here.
 */
public class BallShooterSubsystem extends SubsystemBase {
  private final WPI_TalonSRX ballShooter;
  private Boolean isshooting = false;

  public BallShooterSubsystem(){
    ballShooter = new WPI_TalonSRX(Constants.ballShooterCANID);
    SendableRegistry.setName(ballShooter, "ballShooter");
  }

  public void shoot(){
    isshooting = true;
  }
  public void stop(){
    isshooting = false;
  }

  @Override
  public void periodic() {
    if(isshooting == true){
      //ballShooter.setVoltage(-11);
      ballShooter.set(-1);
    }
    else{
      ballShooter.set(0);
    }
  }

}
