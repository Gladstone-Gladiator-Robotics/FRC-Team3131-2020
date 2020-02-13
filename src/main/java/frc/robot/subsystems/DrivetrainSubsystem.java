/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.kinematics.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DrivetrainSubsystem extends SubsystemBase {
  private  WPI_VictorSPX leftDrive2 = new  WPI_VictorSPX(1); //Left Back
  private  WPI_TalonSRX rightDrive2 = new  WPI_TalonSRX(2); //Right Back
  private  WPI_TalonSRX rightDrive1 = new  WPI_TalonSRX(3); //Right Front
  private  WPI_TalonSRX leftDrive1 = new  WPI_TalonSRX(4); //Left Front
  //private  Talon leftDrive1 = new Talon(0); //Left Back
  //private  Talon leftDrive2 = new Talon(1); //Right Back
  //private  Talon rightDrive1 = new Talon(2); //Right Front
  //private  Talon rightDrive2 = new Talon(3); //Left Front
  private final SpeedController leftGroup;// = new SpeedControllerGroup(leftDrive1, leftDrive2);
  private final SpeedController rightGroup;// = new SpeedControllerGroup(rightDrive1, rightDrive2);
  private final DifferentialDrive driveTrain;
  public double speed;
  public double rotation;
  Encoder rightEncoder = new Encoder(0, 1);
  Encoder leftEncoder = new Encoder(2, 3);
  private final PIDController leftPIDController = new PIDController(1, 1, 0);
  private final PIDController rightPIDController = new PIDController(1, 1, 0);
  private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(1.0, 3.0);
  private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(kTrackWidth);
  private static final double kTrackWidth = 0.479425; //in meters
  private static final double kWheelRadius = 0.075; //in meters
  private static final double kEncoderResolution = 4096;
  private static final double kTransmissionFactor = 0.08974359;
  public DrivetrainSubsystem(SpeedController leftMotor, SpeedController rightMotor) {

    driveTrain = new DifferentialDrive(leftMotor, rightMotor);
    leftGroup = leftMotor;
    rightGroup = rightMotor;

    //rightGroup.setInverted(true);
    //leftEncoder.setDistancePerPulse(2 * Math.PI * kWheelRadius / kEncoderResolution * kTransmissionFactor);
    //rightEncoder.setDistancePerPulse(2 * Math.PI * kWheelRadius / kEncoderResolution * kTransmissionFactor);
 
    //leftEncoder.reset();
    //rightEncoder.reset();
  }

  public void setSpeeds(DifferentialDriveWheelSpeeds speeds) {
    final double leftFeedForward = feedforward.calculate(speeds.leftMetersPerSecond);
    final double rightFeedForward = feedforward.calculate(speeds.rightMetersPerSecond);
    final double leftOutput = leftPIDController.calculate(leftEncoder.getRate() , speeds.leftMetersPerSecond);
    final double rightOutput = rightPIDController.calculate(rightEncoder.getRate() , speeds.rightMetersPerSecond);
    leftGroup.setVoltage(leftOutput + leftFeedForward);
    rightGroup.setVoltage(rightOutput + rightFeedForward);
}

  @Override
  public void periodic() { 
    //setSpeeds(kinematics.toWheelSpeeds(new ChassisSpeeds(speed, 0.0, rotation)));
    driveTrain.arcadeDrive(speed, rotation);
  }
}
