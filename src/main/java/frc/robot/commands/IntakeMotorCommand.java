/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeedMotorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeMotorCommand extends CommandBase {
  private final IntakeSubsystem intakeSystem;
  private final FeedMotorSubsystem feedSystem; 
  public IntakeMotorCommand(IntakeSubsystem intakeSystem, FeedMotorSubsystem feedSystem){
    this.intakeSystem = intakeSystem;
    this.feedSystem = feedSystem;
    addRequirements(intakeSystem, feedSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intakeSystem.intakeMotor.set(-.14);
    if (intakeSystem.ballSensor.getVoltage() > 2){
      feedSystem.start();
    }else{
      feedSystem.stop();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSystem.intakeMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
