package RPN.CALCUL;

import java.util.Stack;

public class Undo implements Command{

	private Stack<Stack<Double>> undo;
	private Stack<Double> pile;
	
	public Undo(final Stack<Double> pile) {
		this.pile = pile;
		undo = new Stack<Stack<Double>>();
	}
	
	public boolean canApply() {
		return !undo.isEmpty();
	}
	
	public void alertChange() {
		@SuppressWarnings("unchecked")
		Stack<Double> s = (Stack<Double>) pile.clone();
		undo.push(s);
	}
	
	private void copyLastStack() {
		for (double d : undo.lastElement()) {
			pile.push(d);
		}
	}
	
	public void apply() {
		while (!pile.isEmpty()) {
			pile.pop();
		}
		undo.pop();
		copyLastStack();
	}

}