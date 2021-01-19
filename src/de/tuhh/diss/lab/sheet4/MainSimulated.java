package de.tuhh.diss.lab.sheet4;

import MazebotSim.MazebotSimulation;
import MazebotSim.Visualization.GuiMazeVisualization;
import lejos.utility.Delay;

public class MainSimulated {

	public static void main(String[] args) {
		MazebotSimulation sim = new MazebotSimulation("Mazes/TestArea.png", 1.5,  1.5);
		GuiMazeVisualization gui = new GuiMazeVisualization(1.5, sim.getStateAccessor());
		sim.scaleSpeed(1);
//		sim.setRobotPosition(0.525, 0.525, 90);
		sim.setRobotPosition(0.75, 0.75, 0);

		sim.startSimulation();
		gui.startVisualization();

		// Here goes your Code!
		//SimpleTurnerTest.main(new String[0]);
		//GyroscopeTurnerTest.main(new String[0]);
		ProportionalTurnerTest.main(new String[0]);
		
		Delay.msDelay(100);
		sim.stopSimulation();
	}

}
