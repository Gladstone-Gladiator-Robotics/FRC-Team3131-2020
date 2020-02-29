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
    public static int leftDrive2CANID = 1;
    public static int rightDrive2CANID = 2;
    public static int rightDrive1CANID = 3;
    public static int leftDrive1CANID = 4;
    public static int colorWheelMotorCANID = 5;
    public static int intakeMotorCANID = 6;
    public static int ballShooterCANID = 7;
    public static int feedMotorCANID = 8;
    public static int climbMotorCANID = 9;
    //solenoids
    public static int intakePiston1Port1 = 2;
    public static int intakePiston1Port2 = 3;
    public static int intakePiston2Port1 = 4;
    public static int intakePiston2Port2 = 5;
}
