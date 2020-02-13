package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;



public class LimelightSubsystem extends SubsystemBase {
   public boolean m_LimelightHasValidTarget = false;
   public double m_LimelightDriveCommand = 0.0;
   public double m_LimelightSteerCommand = 0.0;
    
    public LimelightSubsystem(){

    } 
    public void periodic(){

    }

}
