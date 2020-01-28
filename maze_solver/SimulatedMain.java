package Lab5;

import MazebotSim.MazebotSimulation;
//import Lab5.DisplayMenu;

public class SimulatedMain {

	public static void main(String[] args) {
		MazebotSimulation sim = new MazebotSimulation("Mazes/maze_1_3by4.png", 1.5,  1.13);
		sim.setRobotPosition(0.525, 0.175, 90);
		//sim.setRobotPosition(0.2, 0.175, 90);
		sim.startSimulation();
		
		//MainFrame.main(new String[0]);
		Testphase.main(new String[0]);
		sim.stopSimulation();


	}

}
