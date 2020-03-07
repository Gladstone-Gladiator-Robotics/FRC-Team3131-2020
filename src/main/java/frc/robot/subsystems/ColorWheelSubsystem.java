/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;

import frc.robot.Constants;
import frc.robot.WheelColor;
public class ColorWheelSubsystem extends SubsystemBase {
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private WPI_TalonSRX colorWheelMotor = new WPI_TalonSRX(Constants.colorWheelMotorCANID);
  public DoubleSolenoid colorWheelPiston;
  
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  //Creates a new ExampleSubsystem.
   
  public ColorWheelSubsystem() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    SendableRegistry.setName(colorWheelMotor, "colorWheelMotor");
    SendableRegistry.setName(colorWheelPiston , "colorWheelPiston");

    colorWheelPiston = new DoubleSolenoid(3,2);
  }
  public WheelColor getColor(){
    Color detectedColor = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      return WheelColor.BLUE;
    } else if (match.color == kRedTarget) {
      return WheelColor.RED;
    } else if (match.color == kGreenTarget) {
      return WheelColor.GREEN;
    } else if (match.color == kYellowTarget) {
      return WheelColor.YELLOW;
    } else {
      return WheelColor.UNKNOWN;
    }
  } 
  @Override
  public void periodic() {
    printColors();
  }
  public void spin() {
    colorWheelMotor.set(0.75);
  }

  public void stop(){
    colorWheelMotor.set(0);
  }
  
  public void printColors(){
    Color detectedColor = m_colorSensor.getColor();

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putString("Detected Color", getColor().name());
    // This method will be called once per scheduler run
  }
}