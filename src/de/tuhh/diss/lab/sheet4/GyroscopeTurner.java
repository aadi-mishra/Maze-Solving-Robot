package de.tuhh.diss.lab.sheet4;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class GyroscopeTurner implements Turner{
	
	private EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	private EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	private EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S3);
	
	private int motorSpeed = 0;
	private final int DELAY = 10;

	@Override
	public void setSpeed(int degreesPerSecond) {
		// Assign input parameter to the class variable motorSpeed
		this.motorSpeed = degreesPerSecond;
		// Assign speed values to the motors
		rightMotor.setSpeed(motorSpeed);
		leftMotor.setSpeed(motorSpeed);
	}

	@Override
	public void turn(int degrees) {
		final SampleProvider sp = gyroSensor.getAngleMode();
		int currentAngle;
		
		do {
			float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);
            currentAngle = (int)sample[0];
			double error = degrees - currentAngle;
			
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
			
			LCD.drawString("Motor speed:" + motorSpeed, 1, 2);
			LCD.drawString("Target angle:" + degrees, 1, 3);
			LCD.drawString("Error:" + error, 1, 4);
			Delay.msDelay(DELAY);
			LCD.clear(14, 1, 1);
			
		}while(true);
	}
}
