/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeedMotorSubsystem extends SubsystemBase {
  private final WPI_TalonSRX feedMotor;
  private boolean running = false;
  public FeedMotorSubsystem() {
    feedMotor = new WPI_TalonSRX(8);
    SendableRegistry.setName(feedMotor, "feedMotor");
  }

  public void start(){
    running = true;
  }
  public void stop(){
    running = false;
  }

  @Override
  public void periodic() {
    if (running == true){
      feedMotor.set(1);
    } else {
      feedMotor.set(0);
    }
  }
}
