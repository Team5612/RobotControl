package org.usfirst.frc.team5612.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot
{
	RobotDrive robotDrive; //Defines the class for basic drive operations
	Joystick driveStick; //Defines the class for joy stick control
	
	Relay liftDrive; //Defines the class for the serveo
	Joystick liftStick; //Defines the class for lift joy stick control
	
	int autoLoopCounter;
	
	
	/**
	 * This is the initialization for all robot code
	 */
	public void robotInit()
	{
		robotDrive = new RobotDrive(0,1);
		driveStick = new Joystick(0);
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
		liftDrive = new Relay(3);
		liftStick = new Joystick(3);
		liftDrive.notifyAll();
		
		if(liftDrive.get() == Value.kOff)
		{
			liftDrive.set(Value.kOn);
		}
		else
		{
			liftDrive.set(Value.kOff);
		}
	}
	
	/**
	 * This function is called during user control
	 */
	public void teleopPeriodic()
	{
		robotDrive.arcadeDrive(-driveStick.getY(), driveStick.getX());
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
