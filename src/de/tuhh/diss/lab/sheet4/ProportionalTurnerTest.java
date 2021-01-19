package de.tuhh.diss.lab.sheet4;

public class ProportionalTurnerTest {

	public static void main(String[] args) {
		ProportionalTurner pt = new ProportionalTurner();
		int front  = 0;
		int right = -90;
		int left =  90;
		int back = 180;
		
		pt.turn(left);
		pt.turn(right);
		pt.turn(back);
		pt.turn(front);

	}

}
