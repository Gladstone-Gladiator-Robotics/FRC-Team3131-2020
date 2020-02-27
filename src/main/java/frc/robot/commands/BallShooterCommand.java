/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallShooterSubsystem;
import frc.robot.subsystems.FeedMotorSubsystem;

public class BallShooterCommand extends CommandBase {
  private final BallShooterSubsystem ballSystem;
  private final FeedMotorSubsystem feedSystem;
  private int time;
  public BallShooterCommand(BallShooterSubsystem ballSystem, FeedMotorSubsystem feedSystem) {
    this.ballSystem = ballSystem;
    this.feedSystem = feedSystem;
    addRequirements(ballSystem, feedSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(time < 100){
      ballSystem.shoot();
      time += 1;
    }else{
      feedSystem.start();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ballSystem.stop();
    feedSystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
