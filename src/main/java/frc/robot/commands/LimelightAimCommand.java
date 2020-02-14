/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightAimCommand extends CommandBase {
  private final DrivetrainSubsystem driveTrain;
  private boolean isFinished = false;
  /**
   * Creates a new LimelightAimCommand.
   */
  public LimelightAimCommand(DrivetrainSubsystem driveTrain) {
    this.driveTrain = driveTrain;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Initialize");
    isFinished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Update_Limelight_Tracking();
    printData();
  }

  public void printData() {
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

  public void Update_Limelight_Tracking(){
    // These numbers must be tuned for your Robot!  Be careful!
    final double STEER_K = -0.03;                    // how hard to turn toward the target
    final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast

    double tv = NetworkTableInstance.getDefault().getTable("limelight-ghs").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight-ghs").getEntry("tx").getDouble(0);
    double ty = NetworkTableInstance.getDefault().getTable("limelight-ghs").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight-ghs").getEntry("ta").getDouble(0);

    if (tv < 0.5){
      driveTrain.speed = 0;
      driveTrain.rotation = 0.5;
      System.out.println("Rotating");
    } else if(Math.abs(tx) <= 7) {
      System.out.println("Jamie");
      isFinished = true;
    } else {
      // Start with proportional steering
      System.out.println("Kyler");
      double steer_cmd = tx * STEER_K;
      driveTrain.rotation = steer_cmd;

      // try to drive forward until the target area reaches our desired area
      double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

      // don't let the robot drive too fast into the goal
      if (drive_cmd > MAX_DRIVE){
            drive_cmd = MAX_DRIVE;
      }
      driveTrain.speed = 0;
    }
  }

}
