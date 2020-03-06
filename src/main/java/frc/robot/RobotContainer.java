/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem driveTrainSubsystem;
  private IntakeSubsystem intakeSubsystem;
  private final BallShooterSubsystem ballShooterSubsystem = new BallShooterSubsystem();
  private final ColorWheelSubsystem colorWheelSubsystem;
  private final ClimbSubsystem climbSubsystem;
  private final FeedMotorSubsystem feedMotorSubsystem;
  private XboxController controller = new XboxController(0);
  //private Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
  private final TeleopDriveCommand teleopDriveCommand;
  private final LimelightAimCommand limelightAimCommand;
  private final BallShooterCommand ballShooterCommand;
  private final Command autoCommand;
  private JoystickButton aButton = new JoystickButton(controller, 1);
  private JoystickButton bButton = new JoystickButton(controller, 2);
  private JoystickButton xButton = new JoystickButton(controller, 3);
  private JoystickButton yButton = new JoystickButton(controller, 4);
  private JoystickButton leftBumper = new JoystickButton(controller, 5);
  private JoystickButton rightBumper = new JoystickButton(controller, 6);
  private JoystickButton leftMiddleButton = new JoystickButton(controller, 7);
  private JoystickButton rightMiddleButton = new JoystickButton(controller, 8);
  //private JoystickButton leftJoystickButton = new JoystickButton(controller, 9);
  //private JoystickButton rightJoystickButton = new JoystickButton(controller, 10);
  private DirectionalPad dPad = new DirectionalPad(controller);
  private final boolean isPracticeBot = (new DigitalInput(9)).get();
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  
  public RobotContainer() {
    if(isPracticeBot == false){ 
      WPI_VictorSPX leftDriveFront = new WPI_VictorSPX(Constants.leftDriveFrontCANID); //Left Back
      WPI_TalonSRX leftDriveBack = new WPI_TalonSRX(Constants.leftDriveBackCANID); //Right Front
      WPI_TalonSRX rightDriveFront = new WPI_TalonSRX(Constants.rightDriveFrontCANID); //Right Back
      WPI_VictorSPX rightDriveBack = new WPI_VictorSPX(Constants.rightDriveBackCANID); //Left Front
      SpeedController leftMotor = new SpeedControllerGroup(leftDriveFront, leftDriveBack);
      SpeedController rightMotor = new SpeedControllerGroup(rightDriveFront, rightDriveBack);
      leftDriveFront.setInverted(true);
      leftDriveBack.setInverted(true);
      rightDriveFront.setInverted(true);
      rightDriveBack.setInverted(true);
      driveTrainSubsystem = new DrivetrainSubsystem(leftMotor, rightMotor);
    } else{
      driveTrainSubsystem = new DrivetrainSubsystem(new Talon(0), new Talon(1));
    }
    intakeSubsystem = new IntakeSubsystem();
    colorWheelSubsystem = new ColorWheelSubsystem();
    climbSubsystem = new ClimbSubsystem();
    feedMotorSubsystem = new FeedMotorSubsystem();

    teleopDriveCommand = new TeleopDriveCommand(driveTrainSubsystem, controller);
    limelightAimCommand = new LimelightAimCommand(driveTrainSubsystem, controller);
    ballShooterCommand = new BallShooterCommand(ballShooterSubsystem, feedMotorSubsystem);
    autoCommand = new SequentialCommandGroup(new LimelightAimCommand(driveTrainSubsystem, controller), 
      new BallShooterCommand(ballShooterSubsystem, feedMotorSubsystem));

    SmartDashboard.putBoolean("Is Practice Bot", isPracticeBot); 
    CommandScheduler.getInstance().setDefaultCommand(driveTrainSubsystem, teleopDriveCommand);
    CommandScheduler.getInstance().registerSubsystem(colorWheelSubsystem);
    configureButtonBindings();
  }  

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoysticpixyLightskButton}.
   */
  private void configureButtonBindings() {
    aButton.toggleWhenActive(new IntakeMotorCommand(intakeSubsystem, feedMotorSubsystem));
    bButton.whenHeld(new ClimbCommand(climbSubsystem));
    xButton.whenHeld(new EjectCommand(intakeSubsystem));
    yButton.toggleWhenActive(new ColorWheelPistonCommand(colorWheelSubsystem, true));
    dPad.down.toggleWhenActive(new ExtendIntakeCommand(intakeSubsystem, true));
    dPad.up.toggleWhenActive(new ExtendIntakeCommand(intakeSubsystem, false));
    leftBumper.whenHeld(limelightAimCommand);
    rightBumper.whenHeld(ballShooterCommand);
    leftMiddleButton.toggleWhenActive(new RotateToFMSColorCommand(colorWheelSubsystem));
    rightMiddleButton.toggleWhenActive(new RotateColorWheelThreeTimesCommand(colorWheelSubsystem));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }

  public Command getTeleopCommand(){
    return teleopDriveCommand;
  }
}
