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

public class RotateToFMSColorCommand  {
  public static RotateToColorCommand getCommand(ColorWheelSubsystem subsystem){
    WheelColor desiredColor = getStageThreeColor();
    return new RotateToColorCommand(desiredColor, subsystem);
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
