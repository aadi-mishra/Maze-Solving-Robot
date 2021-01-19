Remember to adjust the size of the maze when switching between mazes. 

Change these lines in your "MainSimulated.java" accordingly:

  MazebotSimulation sim = new MazebotSimulation("Mazes/<maze-file>", <width>,  <height>);
  GuiMazeVisualization gui = new GuiMazeVisualization(<width>, sim.getStateAccessor());

The following table shows the appropriate values 

<mazeFile>		<width>		<height>

4x4_1.png		1.4		1.4
4x4_2.png		1.4		1.4
4x4_3.png		1.4		1.4
TestArea.png		1.5		1.5
3x4_1.png		1.4		1.05
3x6_loop.png		2.1		2.1


