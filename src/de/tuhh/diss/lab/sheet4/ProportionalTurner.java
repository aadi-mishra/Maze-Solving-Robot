package de.tuhh.diss.lab.sheet4;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class ProportionalTurner implements Turner{
	
	private EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	private EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	private EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S3);
	
	private int motorSpeed = 0;
	private final int K_P = 10;
	//private double error;

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
			int error = degrees - currentAngle;
			this.motorSpeed = (int) K_P * Math.abs(error);
			
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

}
