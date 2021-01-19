package de.tuhh.diss.lab.sheet5;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class Gyro {
	private static EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S3);

	public float getAngle() {
		final SampleProvider sp = gyroSensor.getAngleMode();
    	float [] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);
        int angle = (int) sample[0];
		return angle;
	}
}
