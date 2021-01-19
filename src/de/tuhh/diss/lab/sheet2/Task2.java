package de.tuhh.diss.lab.sheet2;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD; 

public class Task2 {
	
	public static void menu(int volume, int frequency, int duration, int play) {
		// Display the menu
		
		LCD.drawString("Menu",  2, 0);
		LCD.drawString("Volume",  2, 1);
		LCD.drawString("Frequency",  2, 2);
		LCD.drawString("Duration",  2, 3);
		LCD.drawString("Play",  2, 4);
		LCD.drawString(" " + volume, LCD.DISPLAY_CHAR_WIDTH - 3, 1);
		LCD.drawString(" " + frequency, LCD.DISPLAY_CHAR_WIDTH - 6, 2);
		LCD.drawString(" " + duration,  LCD.DISPLAY_CHAR_WIDTH - 6, 3);
		
	}

	public static void main(String[] args) {
		
		int volume = 0;
		int frequency = 0; 
		int duration = 0; 
		int play = 0;
		int y = 1;
		
		LCD.drawString(">", 0, y);
		menu(volume, frequency, duration, play);
		
		while (Button.ESCAPE.isUp()){
			
			int ButtonID = Button.waitForAnyPress();
			
			if (ButtonID == 1) {  // Button.UP.isDown()
				y--;
				LCD.clear();
				LCD.drawString(">", 0, y);
				menu(volume, frequency, duration, play);
				
				if(y <= 1) {
					y = 6;
				}	
			}
			else if (ButtonID == 4) { // Button.DOWN.isDown()
				y++;
				LCD.clear();
				LCD.drawString(">", 0, y);
				menu(volume, frequency, duration, play);
				
				if(y >= 5) {
					y = 0;
				}
			}
			
			if(Button.RIGHT.isDown()) {
				
				if(ButtonID == 8) { // Button.RIGHT.isDown()
					
					if(y==1) {
						volume += 10;
						LCD.drawString(">", 0, y);
						//LCD.clear();
						menu(volume, frequency, duration, play);
					}
					else if(y==2) {
						frequency += 100;
						LCD.drawString(">", 0, y);
						//LCD.clear();
						menu(volume, frequency, duration, play);
					}
					else if (y==3) {
						duration += 500;
						LCD.drawString(">", 0, y);
						//LCD.clear();
						menu(volume, frequency, duration, play);
					}
				}
			}
			
			if(Button.LEFT.isDown()) {
				
				if(ButtonID == 16) { // Button.LEFT.isDown()
					
					if(y==1) {
						volume -= 10;
						LCD.drawString(">", 0, y);
						//LCD.clear();
						menu(volume, frequency, duration, play);
					}
					else if(y==2) {
						frequency -= 100;
						LCD.drawString(">", 0, y);
						//LCD.clear();
						menu(volume, frequency, duration, play);
					}
					else if (y==3) {
						duration -= 500;
						LCD.drawString(">", 0, y);
						//LCD.clear();
						menu(volume, frequency, duration, play);
					}
				}
			}
			
			if((Button.ENTER.isDown()) && (y==4)) {
					
					Sound.setVolume(volume);
					Sound.playTone(frequency, duration);
			}
				
		}
	}
}
