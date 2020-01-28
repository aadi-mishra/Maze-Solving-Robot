package Lab5;

import lejos.hardware.Sound;

public class Beep {
	
	public void beepy() {
		Sound.setVolume(100);
		Sound.playTone(500, 1000);
	}
}
