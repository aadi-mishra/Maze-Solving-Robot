package de.tuhh.diss.lab.sheet5;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

/**
 * @author Aadi Nath Mishra, Özge Beyza Albayrak
 * Implements methods for all the movements of the motors as follows 
 * - Forward
 * - Backward
 * - Turn left and right 
 * - Motor Stop
 */

public class Motors {
	
	private EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	private EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	private Gyro gyroObject = new Gyro();
	private UsSensor usObject = new UsSensor();
	
	private static final int K_P = 20;
	private static final int K_F = 20;
	private static final int K_S = 35;
	private static final double MIN_WALL_DIST = -13.5; 
	private static final double MIN_TARGET_DIST = -4;
	
	private int motorSpeed = 0;
	private float currentDistance = 0;
	private float currentAngle = 0;
	private float lastAngle = 0;
	private float initialDistance = 0;
	private float difference  = 0;
	private int angle = 0;
	
	/**
	 * Sets the speed of both left and right motors of the robot.
	 * 
	 * @param degreesPerSecond Input speed of the motors in degrees per second.
	 */
	public void setSpeed(int degreesPerSecond) {
		// Assign input parameter to the class variable motorSpeed
		this.motorSpeed = degreesPerSecond;
		// Assign speed values to the motors
		rightMotor.setSpeed(motorSpeed);
		leftMotor.setSpeed(motorSpeed);
	}
	
	/**
	 * Implements proportional control for turning of the robot using gyroscope data as feedback.
	 * angles with positive sign are counter clockwise and negative sign are clockwise rotation.
	 * 
	 * @param degrees Input angle to be turned by the motor in degrees
	 */
	public void turn(float degrees) {
		lastAngle = lastAngle + degrees;
		
		do {
            currentAngle = gyroObject.getAngle();
			float error = lastAngle - currentAngle;
			this.motorSpeed =  (int) ( K_P * Math.abs(error));
			
			setSpeed(motorSpeed);
			// Turn robot CCW
			if (error > 0) {
				rightMotor.backward();
				leftMotor.forward();
			// Turn robot CW	
			}else if(error < 0) {
				rightMotor.forward();
				leftMotor.backward();
			// Stop the robot if setpoint reached
			}else {
				rightMotor.stop();
				leftMotor.stop();
				break;
			}
			
		}while(true);
	}
	
	/**
	 * Move forward until safe distance limit reached.
	 * The method uses ultrasonic sensor data as feedback
	 * to implement proportional control.
	 */
	public void moveForward() {
		
		do {
			currentDistance = usObject.getDistance();
			difference = (initialDistance-currentDistance);
			int speed = (int) (difference*K_F);
			setSpeed(speed);
			leftMotor.backward();
			rightMotor.backward();

		}while(difference <= MIN_WALL_DIST);
	}
	
	/**
	 * Implements forward motion of robot to move to a certain distance.
	 * NOTE: Positive distance means move backwards and vice-versa.
	 * 
	 * @param distanceToMove Input desired distance to be traversed by the robot in cm.
	 */
	public void moveForwardToDistance(double distanceToMove) {
		angle = angleRotation(distanceToMove);
		setSpeed(700);
		rightMotor.rotate(angle, true);
		leftMotor.rotate(angle, true);
		
		Delay.msDelay(7000);
	}
	
	/**
	 * Implements forward motions for smaller distances
	 * in order to check color targets.
	 * The method uses ultrasonic sensor data as feedback
	 * to implement proportional control.
	 */
	public void moveForwardSlow() {
		
		do {
			currentDistance = usObject.getDistance();
			difference = (initialDistance-currentDistance);
			int speed = (int) (difference*K_S);
			setSpeed(speed);
			leftMotor.backward();
			rightMotor.backward();

		}
		while(difference<=MIN_TARGET_DIST);
	}
	
	/**
	 * Move backward until safe distance limit reached.
	 * The method uses ultrasonic sensor data as feedback 
	 * to implement proportional control.
	 */
	public void moveBackward() {
				
		do {
			currentDistance = usObject.getDistance();
			difference = (initialDistance-currentDistance);
			int speed = (int) (difference*K_F);
			setSpeed(speed);
			leftMotor.forward();
			rightMotor.forward();				

		} while(difference>MIN_WALL_DIST);
	}
	
	/**
	 * Stop the motors when method called.
	 */
	public void stop() {
		setSpeed(0);
		leftMotor.stop();
		rightMotor.stop();
	}
	
	/**
	 * Implements conversion from distance to be traversed in cm to 
	 * angle to be rotated by motors in degrees per second.
	 * 
	 * @param distance Input distance (in cm) of type double.
	 * @return returns the angle to be turned by wheels.
	 */
	
	public int angleRotation(double distance) {
		
		final int MOTOR_GEAR_COG = 8;  											// Number of motor teeth 
		final int WHEEL_GEAR_COG = 24; 											// Number of wheel teeth 
		final double WHEEL_DIAMETER = 5.4; 										// Robot's wheel diameter is D = 5.4 cm.
		final double GEAR_RATIO = WHEEL_GEAR_COG/MOTOR_GEAR_COG; 
		final double PERIMETER = Math.PI * WHEEL_DIAMETER;  					// C = 2*pi*r = pi*D
		double wheelRotation = distance/PERIMETER;
		double motorRotation = GEAR_RATIO * wheelRotation;
		int angle = (int) (motorRotation * (360)); 
		
		return angle;	
	}
}
