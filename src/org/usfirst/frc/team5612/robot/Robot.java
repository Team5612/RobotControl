package org.usfirst.frc.team5612.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot
{  
	// Defines the class for basic drive operations
	RobotDrive robotDrive;
	// Defines the class for joy stick control
	Joystick driveStick;
	// Defines the class for the servo
	TalonSRX liftServo;
	// Defines the class for lift joy stick control
	Joystick liftStick;
	// Defines the class for camera server
	CameraServer server;
	
	int autoLoopCounter;
	
	/**
	 * This is the initialization for all robot code
	 */
	public void robotInit()
	{
		robotDrive = new RobotDrive(0,1);
		driveStick = new Joystick(0);
		
		liftServo = new TalonSRX(3);
		liftStick = new Joystick(3);
		
		server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture("cam0");
	}
	
	/**
	 * This function is run each time the robot goes autonomous
	 */
	public void autonomousInit()
	{
		autoLoopCounter = 0;
	}
	
	/**
	 * This function is used when the robot goes autonomous
	 */
	public void autonomousPeriodic()
	{
		// Check for 100 cycles of the loop
		if(autoLoopCounter < 100)
		{
			// Makes robot drive at quarter speed
			robotDrive.drive(0.6, 0.0);
			autoLoopCounter++;
		}
		else
		{
			// Stops the robot
			robotDrive.drive(0.0, 0.0);
		}
	}
	
	/**
	 * This function is used when the robot goes into user operated mode
	 */
	public void teleopInit()
	{}
	
	/**
	 * This function is called during user control
	 */
	public void teleopPeriodic()
	{	
		// Activates robotDrive and inverts driveStick
		robotDrive.arcadeDrive(-driveStick.getY(), -driveStick.getX());
		// Activates liftServo and inverts liftStick
		liftServo.set(liftStick.getY());
		Timer.delay(0.01);
		robotDrive.setMaxOutput(-driveStick.getThrottle());
	}
	
	/**
	 * This function is called when the robot goes into test mode
	 */
	public void testPeriodic()
	{
		LiveWindow.run();
	}
}
