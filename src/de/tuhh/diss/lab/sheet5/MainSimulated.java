package de.tuhh.diss.lab.sheet5;

import MazebotSim.MazebotSimulation;
import MazebotSim.Visualization.GuiMazeVisualization;
import lejos.utility.Delay;

public class MainSimulated {

	public static void main(String[] args) {
		
//		MazebotSimulation sim = new MazebotSimulation("Mazes/4x4_1.png", 1.4,  1.4);
		MazebotSimulation sim = new MazebotSimulation("Mazes/3x4_1.png", 1.4,  1.05);

		GuiMazeVisualization gui = new GuiMazeVisualization(1.4, sim.getStateAccessor());

		sim.scaleSpeed(1);
//		sim.setRobotPosition(0.525, 0.525, 90);  // robot position for 4x4 maze size 
		sim.setRobotPosition(0.175, 0.175, 90);  // robot position for 3x4 maze size 

		sim.startSimulation();
		gui.startVisualization();

		// Here goes your Code!
		MazeSolver.main(new String[0]);
		
		Delay.msDelay(100);
		sim.stopSimulation();
	}

}
