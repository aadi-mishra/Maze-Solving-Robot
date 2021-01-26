package de.tuhh.diss.lab.sheet5;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

/**
 * @author Aadi Nath Mishra, Özge Beyza Albayrak
 * 
 * Implements method for reading gyroscope data and returning angle values.
 */
public class Gyro {
	
	private static EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S3);
	private int angle = 0;
	
	/**
	 * @return    Returns angle read by gyro sensor.
	 */
	public float getAngle() {
		
		final SampleProvider sp = gyroSensor.getAngleMode();
    	float [] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);
        angle = (int) sample[0];
        
		return angle;
	}
}
