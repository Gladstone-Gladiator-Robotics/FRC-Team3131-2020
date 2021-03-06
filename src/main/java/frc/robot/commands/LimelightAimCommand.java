/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class LimelightAimCommand extends CommandBase {
  
  private static NetworkTableEntry desiredTargetArea;
  private static NetworkTableEntry steerConstant;
  private static NetworkTableEntry driveConstant;
  private static NetworkTableEntry maxDriveSpeed;
  private static NetworkTableEntry maxSteerSpeed;
  private static NetworkTableEntry targetAngleX;
  private static NetworkTableEntry targetAngleY;
  
  private final DrivetrainSubsystem driveTrain;
  private boolean isFinished = false;
  private final XboxController controller;
  /**
   * Creates a new LimelightAimCommand.
   */
  public LimelightAimCommand(DrivetrainSubsystem driveTrain, XboxController controller) {
    this.driveTrain = driveTrain;
    this.controller = controller;
    final ShuffleboardTab tab = Shuffleboard.getTab("Tuning");
    if (desiredTargetArea == null) {
      desiredTargetArea =
        tab.addPersistent("Desired Target Area", 16)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", 0, "max", 25))
        .getEntry();
      steerConstant = 
        tab.addPersistent("Steer Constant", -0.1)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();
      driveConstant =
        tab.addPersistent("Drive Constant", -0.2)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();
      maxDriveSpeed = 
        tab.addPersistent("Max Drive Speed", -0.55)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();
      maxSteerSpeed = 
        tab.addPersistent("Max Steer Speed", 0.55)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();
      targetAngleX = 
        tab.addPersistent("targetAngleX", 2)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", 0, "max", 10))
        .getEntry();
    addRequirements(driveTrain);
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isFinished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    final double STEER_K = steerConstant.getDouble(-0.1);                    // how hard to turn toward the target
    final double DRIVE_K = driveConstant.getDouble(-0.2);                    // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = desiredTargetArea.getDouble(16);        // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = maxDriveSpeed.getDouble(-0.55);                 // Simple speed limit so we don't drive too fast
    final double MAX_STEER = maxSteerSpeed.getDouble(0.55);             
    double tv = NetworkTableInstance.getDefault().getTable("limelight-ghs").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight-ghs").getEntry("tx").getDouble(0);
    //double ty = NetworkTableInstance.getDefault().getTable("limelight-ghs").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight-ghs").getEntry("ta").getDouble(0);
    
    if (tv < 0.5){
      driveTrain.speed = 0;
      driveTrain.rotation = -.55;
    } else if(ta < DESIRED_TARGET_AREA) {
      // Start with proportional steering
      
      double steer_cmd = tx * STEER_K;
      
      driveTrain.rotation = Math.max(Math.min(steer_cmd, MAX_STEER), -MAX_STEER);

      // try to drive forward until the target area reaches our desired area
      double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

      // don't let the robot drive too fast into the goal
      if (drive_cmd < MAX_DRIVE){
            drive_cmd = MAX_DRIVE;
      }
      driveTrain.speed = drive_cmd;
    }
    if (ta >= DESIRED_TARGET_AREA && Math.abs(tx) <= targetAngleX.getDouble(2)){
      controller.setRumble(RumbleType.kLeftRumble, 1);
      Timer timer = new Timer();
      timer.schedule(new RumbleStopper(controller), 500);
      isFinished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.speed = 0;
    driveTrain.rotation = 0;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }

}

  class RumbleStopper extends TimerTask{
    private final XboxController controller;
    public RumbleStopper(XboxController controller){
      this.controller = controller;
    }
    @Override
    public void run() {
      controller.setRumble(RumbleType.kLeftRumble, 0);
    }
  }
 