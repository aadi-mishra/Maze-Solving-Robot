package de.tuhh.diss.lab.sheet5;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class MazeSolverTest {

	// Create object for sensors, motors and usable classes
		static UsSensor usObject = new UsSensor();
		static ColorSensor colorObject = new ColorSensor();
		static Beep beeper = new Beep();
		static DisplayMenu menu = new DisplayMenu();
		static Motors motors = new Motors();
		static Gyro gyroObject = new Gyro();
		
		private static final int RIGHT = -90;
		private static final int LEFT = 90;
		
		public static void main(String[] args) {
						
			float[] distances;
			boolean leftWall;
			boolean frontWall;
			boolean rightWall;
			boolean isTarget;
			
			String targetColor = menu.displayMenu();   // Final Target to reach 
			beeper.beepy();	
			
			do {
				distances = scanScore();
				float forwardDistance = distances[0];
				float leftDistance = distances[1];
				float rightDistance =  distances[2];
				
				LCD.drawString("F "+distances[0] , 0, 4);
				LCD.drawString("L "+distances[1] , 0, 5);
				LCD.drawString("R "+distances[2] , 0, 6);
				
				if (forwardDistance <= 14) {
					frontWall = true;
				}else {
					frontWall = false;
					}
				
				if (leftDistance <= 14) {
					leftWall = true;
				}else {
					leftWall = false;
					}
				
				if (rightDistance <= 14) {
					rightWall = true;
				}else {
					rightWall = false;
					}
				
				// In case of dead-end this routine will follow.
				if (frontWall && leftWall && rightWall) {
					LCD.drawString("Checking Dead Ends", 0, 7);
					motors.turn(LEFT);
					motors.moveForwardSlow();
					isTarget = checkTarget(targetColor);
					if (isTarget) {
						break;
					}else {
						motors.moveBackward();
					}
					
					motors.turn(RIGHT);
					motors.moveForwardSlow();
					isTarget = checkTarget(targetColor);
					if (isTarget) {
						break;
					}else {
						motors.moveBackward();
					}
					motors.turn(RIGHT);
					motors.moveForwardSlow();
					isTarget = checkTarget(targetColor);
					if (isTarget) {
						break;
					}else {
						motors.moveBackward();
						motors.turn(RIGHT);
						motors.moveForward();
					}
				}// Left wall following algorithm
				else if (leftWall == false) {
//					motors.moveForwardSlow();
//					isTarget = checkTarget(targetColor);
//
//					if (isTarget) {
//						break;
//					}
//					motors.moveBackward();
					motors.turn(LEFT);
					motors.moveForward();
					
				}else {
					
					if (frontWall == false) {
						motors.moveForward();
						
					}else {
//						motors.moveForwardSlow();
//						isTarget = checkTarget(targetColor);
//						if (isTarget) {
//							break;
//						}
//						motors.moveBackward();
						motors.turn(RIGHT);
					}
				}			
			}while(true);
		}
										
		public static float[] scanScore() {
			float[] distanceParam  = new float[3];
			distanceParam[0] = usObject.getDistance();
			motors.turn(LEFT);
			distanceParam[1] = usObject.getDistance();
			motors.turn(RIGHT);
			motors.turn(RIGHT);
			distanceParam[2] = usObject.getDistance();
			motors.turn(LEFT);
			
			float currentAngle = gyroObject.getAngle();
			LCD.drawString("curr"+ currentAngle, 0, 7);
			
			return distanceParam;
		}
						
		public static boolean checkTarget(String targetColor) {
			
			boolean target;
			String colorName = colorObject.colorDetect();
			
			if (colorName == targetColor) {
				target = true;
				motors.motorStop();
				LCD.drawString("Target is " + colorName, 0, 0);
				beeper.beepy();
				
			}else {
				target = false;
				LCD.drawString("No Target", 0, 0);
				motors.moveBackward();	
			}
			return target;
		}
}
