package de.tuhh.diss.lab.sheet5;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

/**
 * @author Aadi Nath Mishra, Özge Beyza Albayrak
 */

public class UsSensor {
	
	private static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S4);
	
	private float distance = 0;
	
	/**
	 * Implements method to read the ultrasonic sensor data and return the distance
	 * @return distance measured by the ultrasonic sensor as float data type.
	 */
	public float getDistance() {
		
		SampleProvider sp = us.getDistanceMode();
		
		float [] sample = new float[sp.sampleSize()];
		sp.fetchSample(sample, 0);
		distance  = (float) sample[0]*100;
		
		return distance;	
	}
}
