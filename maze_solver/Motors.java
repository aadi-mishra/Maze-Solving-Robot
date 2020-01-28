package Lab5;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/* This class implements methods for all the movements of the motors as follows 
 * - Forward
 * - Backward
 * - Turn left and right 
 * - Motor Stop
 * 
 */

public class Motors {
	
	public static EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	public static EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	
	public void motorSpeed(int speed) {
		leftMotor.setSpeed(speed);
		rightMotor.setSpeed(speed);
	}
	
	
	public void moveForward() {
		leftMotor.backward();
		rightMotor.backward();
	}
	
	public void moveBackward() {
		leftMotor.forward();
		rightMotor.forward();
	}
	
	public void turnLeft() {
		leftMotor.backward();
		rightMotor.forward();
		
	}
	
	public void turnRight() {
		leftMotor.forward();
		rightMotor.backward();
		
	}
	
	public void motorStop() {
		leftMotor.setSpeed(0);
		rightMotor.setSpeed(0);
	}
		
	public void eKill() {
		leftMotor.stop();
		rightMotor.stop();
		
	}
		
	
}
