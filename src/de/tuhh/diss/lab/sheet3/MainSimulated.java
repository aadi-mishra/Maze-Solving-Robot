package de.tuhh.diss.lab.sheet3;

import MazebotSim.MazebotSimulation;
import MazebotSim.Visualization.GuiMazeVisualization;
import lejos.utility.Delay;

public class MainSimulated {

	public static void main(String[] args) {
		MazebotSimulation sim = new MazebotSimulation("Mazes/maze_1_3by4.png", 1.5,  1.5);
		GuiMazeVisualization gui = new GuiMazeVisualization(1.5, sim.getStateAccessor());
		sim.scaleSpeed(1);
		sim.setRobotPosition(0.525, 0.525, 90);

		sim.startSimulation();
		gui.startVisualization();

		// Here goes your Code!
		Task3_3.main(new String[0]);
		
		Delay.msDelay(100);
		sim.stopSimulation();
	}

}
