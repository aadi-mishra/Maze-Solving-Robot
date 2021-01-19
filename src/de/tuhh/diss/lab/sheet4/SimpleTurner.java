package de.tuhh.diss.lab.sheet4;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class SimpleTurner implements Turner{
	
	private EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	private EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	
	private int motorSpeed = 0;
	
	private final int MOTOR_TEETH = 8;  // number of motor teeth 
	private final int WHEEL_TEETH = 24; // number of wheel teeth 
	private final double WHEEL_DIAMETER = 55; // Robot's wheel diameter is D = 55 mm.
	private final double WHEELS_DISTANCE = 123; // Distance between the center of wheels 123 mm
	private final double GEAR_RATIO = WHEEL_TEETH/MOTOR_TEETH; 
	private final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;  // C = 2*pi*r = pi*D
	
	@Override
	public void setSpeed(int degreesPerSecond) {
		// Assign input parameter to the class variable motorSpeed
		this.motorSpeed = degreesPerSecond;
		// Assign speed values to the motors
		
		rightMotor.setSpeed(motorSpeed);
		leftMotor.setSpeed(motorSpeed);
		LCD.drawString("Motor speed: " + motorSpeed, 2, 2);
	}

	@Override
	public void turn(int degrees) {
		
		LCD.drawString("Turn angle: " + degrees, 2, 3);
		
		final int DEGREES_PER_ROTATION = 360;
		
		double distance  = (Math.PI * WHEELS_DISTANCE * degrees) / DEGREES_PER_ROTATION;  // PI*WHEELS_DISTANCE
		
		double wheelRotation = distance/WHEEL_CIRCUMFERENCE;
		double motorRotation = GEAR_RATIO * wheelRotation;
		
		int angle = (int) Math.round(motorRotation * DEGREES_PER_ROTATION); 
		
		// Counter clockwise turning angle is positive 
		
		rightMotor.rotate(-angle, true); 
		leftMotor.rotate(angle, false);		
	}

}
