package de.tuhh.diss.lab.sheet5;

import lejos.hardware.lcd.LCD;

/**
 * @author Aadi Nath Mishra, Özge Beyza Albayrak
 * 
 * Implements the Wall Follower algorithm for the maze solving robot with left hand rule.
 * Additionally other functionalities such as detecting dead ends, color target, wall detection, etc.
 * are extended for a good navigation and problem solving. 
 */

public class MazeSolver {

// Create object for sensors, motors and usable classes
	private static UsSensor usObject = new UsSensor();
	private static ColorSensor colorObject = new ColorSensor();
	private static Beep beeper = new Beep();
	private static DisplayMenu menu = new DisplayMenu();
	private static Motors motors = new Motors();
	
	private static final int RIGHT = -90;
	private static final int LEFT = 90; 
	private static final int BACK_CW = -180; 
	private static final int MOVE_DISTANCE = -35;
	private static final double TILE_CENTER = 17.5;
	

	
	/**
	 * Main method calls the wallFollower method which navigates through the maze 
	 * and looks for the selected target. 
	 * @param args
	 */
	public static void main(String[] args) {	
		wallFollower();
	}
	
	/**
	 * Method displays the menu and takes the selected target color from the user then 
	 * robot produces a beep to indicate the start of the maze search.
	 * The robot checks for the walls (front, left and right walls) and
	 * stores boolean decision to variables if wall is present.
	 * 
	 * In the Left Hand Rule:
	 * If possible the robot turns left, 
	 * otherwise, it goes straight if possible,
	 * if going left or straight both not possible, turns right, if possible
	 * finally, if none of the above is possible, the robot must be in a dead-end. 
	 * In that case, it checks for the target color on each wall then turn around if the target is not detected.
	 */
	public static void wallFollower() {
		float[] distances;
		boolean leftWall;
		boolean frontWall;
		boolean rightWall;
		boolean isTarget;
		
		String targetColor = menu.displayMenu();   // Final Target to reach 
		LCD.drawString("Target Color: " ,0, 0);
		LCD.drawString(targetColor, 0, 1);
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
			
			// If the robot is in a dead-end this routine will follow.
			if (frontWall && leftWall && rightWall) {
				
				LCD.drawString("Checking dead-end", 0, 7);
				
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
			
		} while(true);
		
	}
	
	/**
	 * The method scans for the walls on its front,left and right direction and assigns the distance values
	 * from the ultrasonic sensor to an array.
	 * 
	 * @return		Array of distance values for each wall.
	 */				
	public static float[] scanScore() {
		float[] distanceParam  = new float[3];
		distanceParam[0] = usObject.getDistance();
		motors.turn(LEFT);
		distanceParam[1] = usObject.getDistance();
		motors.turn(BACK_CW);
		distanceParam[2] = usObject.getDistance();
		motors.turn(LEFT);
				
		return distanceParam;
	}
			
	/**
	 * The method compares the target color with the detected wall color by the robot's color sensor.
	 * If the they are same the motor stops and the robot produces a beep. The program terminates.
	 * 
	 * @param targetColor	 Selected target color by the user.
	 * @return   true, if the detected color is the target color else false.
	 */
	public static boolean checkTarget(String targetColor) {
		
		boolean target;
		String colorName = colorObject.colorDetect();
		
		if (colorName == targetColor) {
			target = true;
			motors.stop();
			LCD.drawString("Target Found: ", 0, 2);
			LCD.drawString(colorName, 0, 3);
			beeper.beepy();
			
		}else {
			target = false;
			LCD.drawString("Target Not Found!", 0, 2);
			motors.moveBackward();	
			LCD.clear(0, 2, 20);
			LCD.clear(0, 7, 20);
		}
		
		return target;
	}
}
