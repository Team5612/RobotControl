package org.usfirst.frc.team5612.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot
{  
	RobotDrive robotDrive; //Defines the class for basic drive operations
	Joystick driveStick; //Defines the class for joy stick control
	
	Talon liftServo;//Defines the class for the serveo
	Joystick liftStick;//Defines the class for lift joy stick control
	
	CameraServer server;
	
	int autoLoopCounter;
	
	
	/**
	 * This is the initialization for all robot code
	 */
	public void robotInit()
	{
		robotDrive = new RobotDrive(0,1);
		driveStick = new Joystick(0);
		
		liftServo = new Talon(3);
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
		if(autoLoopCounter < 100) //Check for 100 cycles of the loop
		{
			robotDrive.drive(-0.5, 0.0); //Makes robot drive at half speed
			autoLoopCounter++;
		}
		else
		{
			robotDrive.drive(0.0, 0.0); //Stops the robot
		}
	}
	
	/**
	 * This function is used when the robot goes into user operated mode
	 */
	public void teleopInit()
	{
		
	}
	
	/**
	 * This function is called during user control
	 */
	public void teleopPeriodic()
	{	
		robotDrive.arcadeDrive(-driveStick.getY(), driveStick.getX());
		liftServo.set(liftStick.getY());
		Timer.delay(0.01);
	}
	
	
	/**
	 * This function is called when the robot goes into test mode
	 */
	public void testPeriodic()
	{
		LiveWindow.run();
	}
	
}