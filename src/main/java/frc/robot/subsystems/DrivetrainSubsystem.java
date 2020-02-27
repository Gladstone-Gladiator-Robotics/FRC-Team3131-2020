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
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;

public class DrivetrainSubsystem extends SubsystemBase {
  private final SpeedController leftGroup;
  private final SpeedController rightGroup;
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
