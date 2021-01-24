package de.tuhh.diss.lab.sheet5;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

/**
 * @author Aadi Nath Mishra, Özge Beyza Albayrak
 * 
 * Implements methods to display list of colors to be selected as a target for the robot,
 * and allow users to select them.
 *
 **/
public class DisplayMenu {
	
	/** 
	 * Display color as a menu on the screen.
	 */
	public void drawColorName() {
			
			LCD.drawString("BLACK", 1, 0);
			LCD.drawString("BLUE", 1, 1);
			LCD.drawString("GREEN", 1, 2);
			LCD.drawString("YELLOW", 1, 3);
			LCD.drawString("RED", 1, 4);
			LCD.drawString("WHITE", 1, 5);
			LCD.drawString("BROWN", 1, 6);		
	}
	
	/**
	 *  Returns color selected by the user as input from the drop-down menu.
	 *  Allows for pointer movement using key press.
	 * @return 		Target color value
	 */
	public String displayMenu() {
		
		final int POINTER_X = 0;
		int pointerY = 0;
		String colorName = "NONE";
		LCD.drawString(">", POINTER_X, pointerY);
		drawColorName();
		while (Button.ENTER.isUp()) {
			
			int buttonID = Button.waitForAnyPress();
			LCD.drawString(">", POINTER_X, pointerY);
			if(buttonID==4) {	
				pointerY++;
				LCD.clear();
				LCD.drawString(">", POINTER_X, pointerY);
				drawColorName();

				if(pointerY>=6) {
					pointerY =-1;
				}

			}else if(buttonID==1){
				pointerY--;
				LCD.clear();
				LCD.drawString(">", POINTER_X, pointerY);
				drawColorName();
	
				if(pointerY<=0) {
					pointerY =7;	
				}
			}else if (buttonID== 2) {
				
				switch(pointerY) {
					case 0: 
						colorName = "BLACK"; 
						break;
					case 1: 
						colorName = "BLUE"; 
						break;
					case 2: 
						colorName = "GREEN"; 
						break;
					case 3: 
						colorName = "YELLOW"; 
						break;
					case 4: 
						colorName = "RED"; 
						break;
					case 5: 
						colorName = "WHITE"; 
						break;
					case 6: 
						colorName = "BROWN"; 
						break;
				}
				LCD.clear();
				break;
			}
		}
		LCD.clear();
	
		return colorName;	
	}	
}
	

