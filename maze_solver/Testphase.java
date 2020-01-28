package Lab5;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Testphase {
	
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
	private static int lastAngle = 0;
	
	private static float currentDistance = 0;
	private static final int K_P = 20;
	private static final int K_F = 15;
	private static final int K_S = 15;
	private static final int COLOR_CHECK = 15;
	
	public static void main(String[] args) {
		
		float[] distances;
		String targetColor = menu.displayMenu();   // Final Target to reach 
		beeper.beepy();	
		motors.motorSpeed(200);
		motors.moveForward();
		Delay.msDelay(1000);
		distances = scanScore();
		//LCD.drawInt(initialAngle, 0, 4);
		//moveForColor(targetColor);
		LCD.drawInt(initialAngle, 0, 6);
		
		float forwardDistance = distances[0];
		float leftDistance = distances[1];
		float rightDistance =  distances[2];
		float farthest = largest(distances);
		propForward();
		checkTarget(targetColor);
		
		
		while(Button.ESCAPE.isUp()) {

			currentAngle = (int) gyroObject.getAngle();
			
			
			//moveFarthest(farthest,forwardDistance,leftDistance ,rightDistance,targetColor);
		
			distances = scanScore();
			forwardDistance = distances[0];
			leftDistance = distances[1];
			rightDistance =  distances[2];
			farthest = largest(distances);
			
			if (forwardDistance > leftDistance && forwardDistance > rightDistance) {
				propForward();
			}
			else if (leftDistance > forwardDistance  && leftDistance > rightDistance) {
				propTurnLeft();
				propForward();
			}
			else if (rightDistance > leftDistance && rightDistance > leftDistance) {
				propTurnRight();
				propForward();
				 
			}
	}

	}
	
	

	
	
	public static void propTurnLeft() {
		
		int diffAngle;
		do {
			currentAngle = (int) gyroObject.getAngle();
			
			diffAngle = (initialAngle - currentAngle);
			
			speed = (diffAngle)*K_P  ;
			
			//LCD.drawInt(diffAngle, 0, 1);
			
			motors.motorSpeed(speed);
			motors.turnLeft();

		}
		
		while (diffAngle > 0);
		initialAngle = -currentAngle;
	}
	
	public static void propTurnLeft0() {
		initialAngle = 0;
		int diffAngle;
		do {
			currentAngle = (int) gyroObject.getAngle();
			diffAngle = (currentAngle - initialAngle);
			speed = (diffAngle)*K_P  ;
		
			LCD.drawInt(currentAngle, 0, 1);

			motors.motorSpeed(speed);
			motors.turnLeft();
		}
		
		while (diffAngle < 0); /// SIGN CHANGE DUE TO DIRECTIONS
	}
	
	public static void propTurnRight() {
		initialAngle = -90;
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
		initialAngle = 0;
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
		propTurnLeft();
		distanceParam[1] = usObject.getDistance();
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
			//checkTarget(targetColor);	
		}
		else if(farthest == leftDistance) {
			propTurnLeft();
			propForward();
			propForwardSlow();
			//checkTarget(targetColor);
			
		}
		else if (farthest == rightDistance) {
			propTurnRight();
			propForward();
			propForwardSlow();
			//checkTarget(targetColor);
		}
		else {
			LCD.clear();
			LCD.drawString("No Distance Detected", 3, 4);
		}
	}
	
	public static void moveForColor(String targetColor ) {
		float distanceFwd;
		int flag = 0;
		initialAngle = initialAngle + 90;
		distanceFwd = usObject.getDistance();
		while (flag <=  1000) {
			distanceFwd = usObject.getDistance();
			
			if (distanceFwd > COLOR_CHECK) {
				
				LCD.drawString("FAR", 0, 5);
				propTurnLeft();
				//propForward();
				distanceFwd = usObject.getDistance();
				
				//initialAngle += 90;
				
			}
			
			distanceFwd = usObject.getDistance();
			
			if( distanceFwd <= COLOR_CHECK) {
				
				LCD.drawString("CLOSE", 0, 6);
				propForwardSlow();
				checkTarget(targetColor);
				propTurnLeft();
				//propForwardSlow();
				//checkTarget(targetColor);
				distanceFwd = usObject.getDistance();
				initialAngle = (-initialAngle) + 90;
			flag +=1;
				
			}	
			
			
			flag+=1;
		}
		motors.motorStop();
		
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