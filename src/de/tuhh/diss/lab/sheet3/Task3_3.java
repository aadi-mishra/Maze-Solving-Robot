package de.tuhh.diss.lab.sheet3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Task3_3 {

	// Robot Configuration 

			public static EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.C);
			public static EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
			
			public static void main(String[] args) {
				//US sensor initial params
				EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S4);
				final SampleProvider ultrasonicSensorSp = ultrasonicSensor.getDistanceMode(); // Object for US sensor 
				float distance = 0; // Distance variable for US sensor 
				float [] sample = new float[ultrasonicSensorSp.sampleSize()]; // Array to store US sensor data
				
				String colorName = "";
				final double MINIMUM_DISTANCE = 0.05;
				final int MOTOR_SPEED = 300;
						
				while (Button.ESCAPE.isUp()) {
					// For Ultrasonic sensor data
					ultrasonicSensorSp.fetchSample(sample, 0);
					distance  = sample[0];
					LCD.drawString("Obstacle at " + (distance), 0 , 2); 
					
					if ((distance)>=MINIMUM_DISTANCE) {
						
						rightMotor.setSpeed(MOTOR_SPEED);
						leftMotor.setSpeed(MOTOR_SPEED);
						rightMotor.backward();
						leftMotor.backward();	
					} else {
						rightMotor.setSpeed(0);
						leftMotor.setSpeed(0);
						
						colorName = colorData();
						
					LCD.drawString("Color Detected is " + colorName, 0, 0);
					Delay.msDelay(300);
					LCD.clearDisplay();	
					}
				ultrasonicSensor.close();
				leftMotor.close();
				rightMotor.close();
				}
			}
			
			public static String colorData() {
				EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
				SensorMode color = colorSensor.getColorIDMode();
				//colorSensor.setFloodlight(false);
				float[] sample = new float[color.sampleSize()];
				
				int colorID=0;
				
				String colorName = "";
				
				while(Button.ESCAPE.isUp()) {
					colorSensor.fetchSample(sample,0);
					colorID = (int)sample[0];
					switch(colorID) {
					
					case Color.NONE:
						colorName = "NONE";
						break;
					case Color.BLACK:
						colorName = "BLACK";
						break;
					case Color.BLUE:
						colorName = "BLUE";
						break;
					case Color.GREEN:
						colorName = "GREEN";
						break;
					case Color.YELLOW:
						colorName = "YELLOW";
						break;
					case Color.RED:
						colorName = "RED";
						break;
					case Color.WHITE:
						colorName = "WHITE";
						break;
					case Color.BROWN:
						colorName = "BROWN";
						break;
					}
				}
				colorSensor.close();
				return colorName;
			}
}
