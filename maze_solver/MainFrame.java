package Lab5;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class MainFrame {
	
	// Create object for sensors, motors and usable classes
	static Gyro gyroObject = new Gyro();
	static UsSensor usObject = new UsSensor();
	static ColorSensor colorObject = new ColorSensor();
	static Beep beeper = new Beep();
	static DisplayMenu menu = new DisplayMenu();
	static Motors motors = new Motors();
	
	// Initialize Parameters for the Class
	private static int speed;
	private static  int initialAngle = 90;
	private static int currentAngle = 0;
	private static float initialDistance;
	private static float currentDistance = 0;
	private static final int K_P = 20;
	private static final int K_F = 15;
	private static final int K_S = 15;
	
	public static void main(String[] args) {
		
		float[] distances;
		String targetColor = menu.displayMenu();   // Final Target to reach 
		beeper.beepy();	
		distances = scanScore();
		
		float forwardDistance = distances[0];
		float leftDistance = distances[1];
		float rightDistance =  distances[2];
		float farthest = largest(distances);
		
		while(Button.ESCAPE.isUp()) {
			
			//currentAngle = (int) gyroObject.getAngle();
			//LCD.drawString(" Init "+currentAngle, 3, 4);
			//initialAngle = 90;
			//propTurnLeft();
			//LCD.drawString(" Left "+currentAngle, 3, 5);
			//initialAngle = -90;
			//propTurnRight();
			//LCD.drawString(" Init "+initialAngle, 3, 4);
			
			LCD.drawString("F "+distances[0] , 0, 4);
			LCD.drawString("L "+distances[1] , 0, 5);
			LCD.drawString("R "+distances[2] , 0, 6);
			
			moveFarthest(farthest,forwardDistance,leftDistance ,rightDistance,targetColor);
			
			distances = scanScore();
			forwardDistance = distances[0];
			leftDistance = distances[1];
			rightDistance =  distances[2];
			farthest = largest(distances);
						
		}
		
	}
	
	public static void propTurnLeft() {
		//int refAngle = 90;
		int diffAngle;
		do {
			currentAngle = (int) gyroObject.getAngle();
			diffAngle = (initialAngle - currentAngle);
			
			speed = (diffAngle)*K_P;
			
			LCD.drawInt(diffAngle, 0, 1);
			
			motors.motorSpeed(speed);
			motors.turnLeft();

		}
		
		while (diffAngle > 0);
		initialAngle = -currentAngle;
	}
	
	public static void propTurnLeft0() {
		int diffAngle;
		do {
			diffAngle = (currentAngle -initialAngle);
			initialAngle = currentAngle;
			speed = (diffAngle)*K_P;
			
			LCD.drawInt(currentAngle, 0, 1);
			
			motors.motorSpeed(speed);
			motors.turnLeft();
		}
		
		while (diffAngle<0);
	}
	
	public static void propTurnRight() {
		//int refAngle = -90;
		int diffAngle;
		do {
			currentAngle = (int) gyroObject.getAngle();
			diffAngle = (initialAngle - currentAngle );
			
			speed = (diffAngle)*K_P;
		
			LCD.drawInt(diffAngle, 0, 1);
			//LCD.clearDisplay();
			//Delay.msDelay(100);
			
			motors.motorSpeed(speed);
			motors.turnRight();
		}
		
		while (diffAngle < 0 ); /// SIGN CHANGE DUE TO DIRECTIONS
		initialAngle = -currentAngle;
	}
	
	public static void propTurnRight0() {
		//initialAngle = 0;
		int diffAngle;
		do {
			currentAngle = (int) gyroObject.getAngle();
			diffAngle = (currentAngle - initialAngle);
			speed = (diffAngle)*K_P;
		
			LCD.drawInt(currentAngle, 0, 1);

			motors.motorSpeed(speed);
			motors.turnRight();
		}
		
		while (diffAngle > 0); /// SIGN CHANGE DUE TO DIRECTIONS
	}
	
	public static void propForward() {
		initialDistance = 0;
		float difference  = 0;
		do {
			currentDistance = usObject.getDistance();
			difference = (initialDistance-currentDistance);
			speed = (int) (difference*K_F);
			motors.motorSpeed(speed);
			motors.moveForward();
			
			LCD.drawString(""+ difference, 0, 1);
			LCD.drawInt(speed, 0, 3);

		}
		while(difference<=-7);
	}
	
	public static void propForwardSlow() {
		initialDistance = 0;
		float difference  = 0;
		do {
			currentDistance = usObject.getDistance();
			difference = (initialDistance-currentDistance);
			speed = (int) (difference*K_S);
			motors.motorSpeed(speed);
			motors.moveForward();
			
			LCD.drawString(""+ difference, 0, 1);
			LCD.drawInt(speed, 0, 3);

		}
		while(difference<=-3);
	}
	
	public static void propBackward() {
		initialDistance = 0;
		float difference  = 0;		
		do {
			currentDistance = usObject.getDistance();
			difference = (initialDistance-currentDistance);
			speed = (int) (difference*K_F);
			motors.motorSpeed(speed);
			motors.moveBackward();	
			
			LCD.drawString(""+ difference, 0, 1);
			LCD.drawInt(speed, 0, 3);
		}
		while(difference>-8);
		
	}
	
	public static float[] scanScore() {
		float[] distanceParam  = new float[3];
		distanceParam[0] = usObject.getDistance();
		initialAngle = 90;
		propTurnLeft();
		distanceParam[1] = usObject.getDistance();
		//initialAngle = -90;
		propTurnRight();
		propTurnLeft0();
		distanceParam[2] = usObject.getDistance();
		return distanceParam;
		
		
	}
	
	public static float largest(float[] inputArray) {
		float farthest;
		farthest = inputArray[0];
		for(int i = 1; i < inputArray.length;i++)
		{
			if(inputArray[i] > farthest)
			{
				farthest = inputArray[i];
			}
		}
		return farthest ;	
	}
	
	public static void moveFarthest(float farthest,float forwardDistance,float leftDistance , float rightDistance, String targetColor) {
		
		if (farthest == forwardDistance) {
			propForward();
			propForwardSlow();
			checkTarget(targetColor);	
		}
		else if(farthest == leftDistance) {
			propTurnLeft();
			propForward();
			propForwardSlow();
			checkTarget(targetColor);
			
		}
		else if (farthest == rightDistance) {
			propTurnRight();
			propForward();
			propForwardSlow();
			checkTarget(targetColor);
		}
		else {
			LCD.clear();
			LCD.drawString("No Distance Detected", 3, 4);
		}
	}
	
	public static boolean moveForColor(float[] distances) {
		
		if (distances[0]<=10) {
			propForwardSlow();
		}
		return false;	
	}
	
	public static void checkTarget(String targetColor) {
		
		String colorName = colorObject.colorDetect();
		
		if (colorName == targetColor) {
			motors.motorStop();
			LCD.drawString("Target is " + colorName, 0, 0);
			beeper.beepy();
		}
		
		else {
			LCD.drawString("No Target", 0, 0);
			propBackward();
			
		}
	}
}