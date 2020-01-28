package Lab5;


import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;


public class UsSensor {
	
	private static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S4);
	
	public float getDistance() {
		final SampleProvider sp = us.getDistanceMode();
		float distance = 0;
		float [] sample = new float[sp.sampleSize()];
		//while(Button.ESCAPE.isUp()) {
			
		sp.fetchSample(sample, 0);
		distance  = (float) sample[0]*100;
		
		return distance;
		
		
	}

}
