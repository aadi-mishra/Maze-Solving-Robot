package de.tuhh.diss.lab.sheet3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Task3_4 {

	//Robot Configuration
	private static EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S3);

	//Configuration
	private static int HALF_SECOND = 500;

	public static void main(String[] args) {
		final SampleProvider sp = gyroSensor.getAngleMode();
		int angle = 0;

        //Control loop
        //final int iteration_threshold = 20;
        while(Button.ESCAPE.isUp()) {

        	float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);
            angle = (int)sample[0];
            
            LCD.drawString("Gyro Angle is  "+ angle, 0, 0);

            Delay.msDelay(HALF_SECOND);
            LCD.clearDisplay();
        }

        gyroSensor.close();
	}

}
