package RPN.CALCUL;

import java.util.Stack;

public class Undo implements Command {
	private Stack<Stack<Double>> undo;
	private Stack<Double> pile;
	
	public Undo() {
		pile = new Stack<Double>();
		undo = new Stack<Stack<Double>>();
	}
	private void suppDernierCmd() {
		for (double p : undo.lastElement()) {
			pile.push(p);
		}
	}
	public void apply() {
		while (!pile.isEmpty()) {
			pile.pop();
		}
		undo.pop();
		suppDernierCmd();
	}
	

}
