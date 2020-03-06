/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.WheelColor;
import frc.robot.subsystems.ColorWheelSubsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
public class RotateToFMSColorCommand extends CommandBase {
  private final ColorWheelSubsystem subsystem;
  public RotateToFMSColorCommand(ColorWheelSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsystem.spin();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (subsystem.getColor() == getStageThreeColor());
    // if sensor is jittery then this won't work
  }

  public static WheelColor getStageThreeColor(){
    

    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0){
      switch (gameData.charAt(0)){
        case 'B' :
          return WheelColor.RED;
        case 'G' :
          return WheelColor.YELLOW;
        case 'R' :
          return WheelColor.BLUE;
        case 'Y' :
          return WheelColor.GREEN;
        default :
          return WheelColor.UNKNOWN;
      }
    } 
    else {
      return WheelColor.UNKNOWN;
    }
  }
}
