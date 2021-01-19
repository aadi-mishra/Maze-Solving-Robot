package de.tuhh.diss.lab.sheet3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Task3_2 {

	private static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S4);
	private static EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	private static EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	
	public static void main(String[] args) {
		
		
		final SampleProvider sp = us.getDistanceMode();
		float distance = 0;
		float [] sample = new float[sp.sampleSize()];
		while(Button.ESCAPE.isUp()) {
			
			sp.fetchSample(sample, 0);
			distance  = (float) sample[0];
			//LCD.drawString("Obstacle at " + (sample[0]*100), 0 , 0);
			LCD.drawString("Obstacle at " + (distance*100) +"cm", 0 , 1);
			
			if ((distance*100)>=4.0) {
				
				rightMotor.setSpeed(300);
				leftMotor.setSpeed(300);
				//rightMotor.rotate(calculateRpm(), true);
				//leftMotor.rotate(calculateRpm(), false);
				rightMotor.backward();
				leftMotor.backward();
				
			}
				
			else {
				rightMotor.setSpeed(0);
				leftMotor.setSpeed(0);
				
				
				//Delay.msDelay(2000);
				}
				
			Delay.msDelay(200);
			LCD.clearDisplay();
			
			
			
			
			
			
		}
			us.close();
			leftMotor.close();
			rightMotor.close();
	}
}
