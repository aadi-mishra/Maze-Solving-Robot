package de.tuhh.diss.lab.sheet3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class Task3_1 {

	public static void main(String[] args) {
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
			
			case Color.NONE: colorName = "NONE"; break;
			case Color.BLACK: colorName = "BLACK"; break;
			case Color.BLUE: colorName = "BLUE"; break;
			case Color.GREEN: colorName = "GREEN"; break;
			case Color.YELLOW: colorName = "YELLOW"; break;
			case Color.RED: colorName = "RED"; break;
			case Color.WHITE: colorName = "WHITE"; break;
			case Color.BROWN: colorName = "BROWN"; break;
			
			//LCD.drawString(colorName, 0, 0);
		
			//LCD.drawString(colorID + " - " + colorName,0, 0);
			}
		LCD.drawString(colorID + " - " + colorName,0, 0);
		Delay.msDelay(500); 
        LCD.clearDisplay();
		
		}
		colorSensor.close();

	}

}
