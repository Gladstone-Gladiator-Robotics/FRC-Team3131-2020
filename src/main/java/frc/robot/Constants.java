/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed,9to reduce verbosity.
 */
public final class Constants {
    //motors
    public static int leftDriveFrontCANID = 1;
    public static int leftDriveBackCANID = 2;
    public static int rightDriveFrontCANID = 6;
    public static int rightDriveBackCANID = 7;
    public static int climbMotorCANID = 3;
    public static int feedMotorCANID = 4;
    public static int colorWheelMotorCANID = 5;
    public static int intakeMotorCANID = 8;
    public static int ballShooterCANID = 9;
    
    //solenoids
    public static int intakePistonPort1 = 1;
    public static int intakePistonPort2 = 0;
}