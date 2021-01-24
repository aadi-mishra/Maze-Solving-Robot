package de.tuhh.diss.lab.sheet5;

import lejos.hardware.Sound;

/**
 * @author Aadi Nath Mishra, Özge Beyza Albayrak
 * 
 * Implements beep functionality of the Lego mindstorm EV3 robot.
 */
public class Beep {

	public void beepy() {
		Sound.setVolume(100);            // Set volume to 100
		Sound.playTone(500, 1000);		 // (frequency in Hz- 500, Duration in ms = 1000) 
	}
}
