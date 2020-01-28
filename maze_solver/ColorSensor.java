package Lab5;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;

/* This Class handles Color
 * 
 */
public class ColorSensor {
	
	public String colorDetect() {
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
		SensorMode color = colorSensor.getColorIDMode();
		float[] sample = new float[color.sampleSize()];
		int colorID=0;
		String colorName = "";
		
		colorSensor.fetchSample(sample,0);
		colorID = (int)sample[0];
		
		switch(colorID) {
			
			case Color.NONE: colorName = "NONE"; 
			break;
			case Color.BLACK: colorName = "BLACK"; 
			break;
			case Color.BLUE: colorName = "BLUE"; 
			break;
			case Color.GREEN: colorName = "GREEN"; 
			break;
			case Color.YELLOW: colorName = "YELLOW"; 
			break;
			case Color.RED: colorName = "RED"; 
			break;
			case Color.WHITE: colorName = "WHITE";
			break;
			case Color.BROWN: colorName = "BROWN"; 
			break;
		}
	
		colorSensor.close();
		
		return colorName;		
	}

}
