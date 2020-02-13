/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.SPI;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem driveTrainSubsystem= new DrivetrainSubsystem();
  private final SolenoidSubsystem solenoidSubsystem= new SolenoidSubsystem();
  private final BallShooterSubsystem ballShooterSubsystem = new BallShooterSubsystem();
  private final ColorWheelSubsystem colorSensorSubsystem = new ColorWheelSubsystem();
  private XboxController controller = new XboxController(0);
  private Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
  private final TeleopDriveCommand TeleopDriveCommand = new TeleopDriveCommand(driveTrainSubsystem, controller);
  private JoystickButton aButton = new JoystickButton(controller, 1);
  private JoystickButton xButton = new JoystickButton(controller, 3);
  private JoystickButton yButton = new JoystickButton(controller, 4);
  private final boolean isPracticeBot = (new DigitalInput(9)).get();
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    SmartDashboard.putBoolean("Is Practice Bot", isPracticeBot); 
    CommandScheduler.getInstance().setDefaultCommand(driveTrainSubsystem, TeleopDriveCommand);
    CommandScheduler.getInstance().registerSubsystem(colorSensorSubsystem);
    configureButtonBindings();
  }  

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoysticpixyLightskButton}.
   */
  private void configureButtonBindings() {
    //xButton.toggleWhenActive(new ExtendPistonCommand(solenoidSubsystem));
    //yButton.toggleWhenActive(new TurnAroundCommand(driveTrainSubsystem, gyro));
    aButton
      .whenPressed(() -> ballShooterSubsystem.shoot())
      .whenReleased(() -> ballShooterSubsystem.stop());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return TeleopDriveCommand;
  }
}
