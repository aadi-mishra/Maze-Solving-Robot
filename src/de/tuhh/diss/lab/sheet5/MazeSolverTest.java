package de.tuhh.diss.lab.sheet5;

import lejos.hardware.lcd.LCD;

/**
 * @author Aadi Nath Mishra, Özge Beyza Albayrak
 * 
 * Implements the Wall Follower algorithm for the maze solving robot with left hand rule.
 * Additionally other functionalities such as detecting dead ends, color target, wall detection, etc.
 * are extended for a good navigation and problem solving. 
 */

public class MazeSolverTest {

	// Create object for sensors, motors and usable classes
		private static UsSensor usObject = new UsSensor();
		private static ColorSensor colorObject = new ColorSensor();
		private static Beep beeper = new Beep();
		private static DisplayMenu menu = new DisplayMenu();
		private static Motors motors = new Motors();
		private static Gyro gyroObject = new Gyro();
		
		private static final int RIGHT = -90;
		private static final int LEFT = 90; 
		private static final int BACK_CW = -180; 
		private static final int MOVE_DISTANCE = -35;
		private static final double TILE_CENTER = 17.5;
		
		public static void main(String[] args) {	
			wallFollower();
		}
		
		public static void wallFollower() {
			
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
				
				if (forwardDistance <= TILE_CENTER) {
					frontWall = true;
				}else {
					frontWall = false;
					}
				
				if (leftDistance <= TILE_CENTER) {
					leftWall = true;
				}else {
					leftWall = false;
					}
				
				if (rightDistance <= TILE_CENTER) {
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
						motors.moveForwardToDistance(MOVE_DISTANCE);
					}
				}else if (leftWall == false) {

					motors.turn(LEFT);
					motors.moveForwardToDistance(MOVE_DISTANCE);
					
				}else {	
					if (frontWall == false) {
						motors.moveForwardToDistance(MOVE_DISTANCE);
						
					}else {

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
			motors.turn(BACK_CW);
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
				motors.stop();
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
