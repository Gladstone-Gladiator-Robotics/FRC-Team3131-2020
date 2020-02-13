/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.*;

public class ExtendIntakeCommand extends CommandBase {
  private final IntakeSubsystem subsystem;
  private Boolean extend;
  /**
   * Creates a new ExtendPistonCommand.
   */
  public ExtendIntakeCommand(IntakeSubsystem subsystem, Boolean extend) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.subsystem = subsystem;
    this.extend = extend;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   if(extend = true){
    subsystem.intakePiston.set(DoubleSolenoid.Value.kForward);
   } else{
    subsystem.intakePiston.set(DoubleSolenoid.Value.kReverse);
   }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.intakePiston.set(DoubleSolenoid.Value.kOff);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return CommandScheduler.getInstance().timeSinceScheduled(this) > 2;
  }
}
