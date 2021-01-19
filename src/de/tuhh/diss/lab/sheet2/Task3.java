package de.tuhh.diss.lab.sheet2;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Task3 {
		
	// Get motor object
	public static EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C); // Starting Right motor 
	public  static EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B); // Starting Left motor 
	
	public static void main(String[] args) {
		
		// block the thread until a button is pressed
		//Button.waitForAnyPress();
		
		// set motor to move 100 degrees per second
		rightMotor.setSpeed(300);
		leftMotor.setSpeed(300);
		
		// Start turning forward or backward
		rightMotor.forward();
		//rightMotor.backward();
		
		leftMotor.forward();
		//leftMotor.backward();
        
		//Rotate a given angle
		
		int angle = angleRotation(30);

		rightMotor.rotate(angle, true);
		leftMotor.rotate(angle, true);
		
		Delay.msDelay(100000);

		rightMotor.rotate(angle, true);
		leftMotor.rotate(angle, true);
		
		//check if motor is still busy moving
		rightMotor.isMoving();
		leftMotor.isMoving();
		
//        rightMotor.stop();
//        leftMotor.stop();
		
	}
	
	public static int angleRotation(double distance) {
		
		final int MOTOR_GEAR_COG = 8;  // number of motor teeth 
		final int WHEEL_GEAR_COG = 24; // number of wheel teeth 
		final double WHEEL_DIAMETER = 5.4; // Robot's wheel diameter is D = 5.4 cm.
		final double GEAR_RATIO = WHEEL_GEAR_COG/MOTOR_GEAR_COG; 
		final double PERIMETER = Math.PI * WHEEL_DIAMETER;  // C = 2*pi*r = pi*D
		
		double wheelRotation = distance/PERIMETER;
		double motorRotation = GEAR_RATIO * wheelRotation;
		
		int angle = (int) (motorRotation * (360)); 
		
		return angle;
		
	}

}
