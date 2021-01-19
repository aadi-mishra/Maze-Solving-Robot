package de.tuhh.diss.lab.sheet2;
import lejos.hardware.lcd.LCD; 
import lejos.utility.Delay;

public class Task1 {

	public static void main(String[] args) {
		
		for (int i = 0; i < LCD.DISPLAY_CHAR_WIDTH - 12 ; i++) {
			
			for (int j = 0; j < LCD.DISPLAY_CHAR_DEPTH - 1 ; j++) {
		
				LCD.drawString("Hello World!",  i, j);
				
				Delay.msDelay(1000); 
				LCD.clear();
			}
		}
				
		
	}

}
