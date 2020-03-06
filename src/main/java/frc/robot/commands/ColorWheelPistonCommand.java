/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

public class ColorWheelPistonCommand extends CommandBase {
  private final ColorWheelSubsystem subsystem;
  private boolean extend;
  private int time;
  public ColorWheelPistonCommand(ColorWheelSubsystem subsystem, Boolean extend) {
    this.extend = extend;
    this.subsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(extend == true){
      subsystem.colorWheelPiston.set(DoubleSolenoid.Value.kForward);
    }else{
      subsystem.colorWheelPiston.set(DoubleSolenoid.Value.kReverse);
    }
    time +=1;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.colorWheelPiston.set(DoubleSolenoid.Value.kOff);
    extend = !extend;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (time >= 50);
  }
}
