/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.*;


/**
 * Add your docs here.
 */
public class BallShooterSubsystem extends SubsystemBase {
private Talon ballshooter = new Talon(5);
private Boolean isshooting = false;
  public void shoot(){
    isshooting = true;
  }
  public void stop(){
    isshooting = false;
  }

  @Override
  public void periodic() {
    if(isshooting ){
      ballshooter.set(1);
    }
    else{
      ballshooter.set(0);
    }
  }

}
