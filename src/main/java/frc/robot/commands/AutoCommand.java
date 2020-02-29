/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BallShooterSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.FeedMotorSubsystem;

public class AutoCommand extends SequentialCommandGroup {
  public AutoCommand(DrivetrainSubsystem subsystem1,
    BallShooterSubsystem subsystem2, 
    FeedMotorSubsystem subsystem3, 
    AutoLimelightAimCommand autoLimelightAimCommand,
    AutoBallShooterCommand autoBallShooterCommand
  ) {
    super(
      autoLimelightAimCommand,
      autoBallShooterCommand
    );
  }
}