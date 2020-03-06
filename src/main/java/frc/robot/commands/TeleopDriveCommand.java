/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.Map;

/**
 * An example command that uses an example subsystem.
 */
public class TeleopDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DrivetrainSubsystem m_subsystem;
  private NetworkTableEntry normalSpeedMultiplier;
  private final XboxController controller = new XboxController(0);
  private JoystickButton leftJoystickButton = new JoystickButton(controller, 9);
  public double speed;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TeleopDriveCommand(DrivetrainSubsystem subsystem, XboxController controller) {
    m_subsystem = subsystem;
    final ShuffleboardTab tab = Shuffleboard.getTab("Tuning");
    normalSpeedMultiplier=
      tab.addPersistent("Normal Speed Multiplier", 0.6)
        .withWidget(BuiltInWidgets.kTextView)
        .withProperties(Map.of("min", 0, "max", 1))
        .withSize(4, 1)
        .getEntry();
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
    if(leftJoystickButton.get()){
      m_subsystem.speed = -controller.getY(Hand.kLeft);
      m_subsystem.rotation = -controller.getX(Hand.kLeft);
    } 
    else{
      m_subsystem.speed =  normalSpeedMultiplier.getDouble(0.6) * controller.getY(Hand.kLeft);
      m_subsystem.rotation = -normalSpeedMultiplier.getDouble(0.6) * controller.getX(Hand.kLeft);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
