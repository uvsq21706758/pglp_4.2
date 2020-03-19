package RPN.CALCUL;

import java.util.Stack;

public class Undo implements Command{

	private Stack<Stack<Double>> undo;
	private Stack<Double> pile;
	
	public Undo(final Stack<Double> pile) {
		this.pile = pile;
		undo = new Stack<Stack<Double>>();
	}
	public void apply() {
	   while (!pile.isEmpty()) {
			   pile.pop();
	   }
		undo.pop();
		for (double db : undo.lastElement()) {
			pile.push(db);
		}
	}
	public void changewarning() {
		@SuppressWarnings("unchecked")
		Stack<Double> stack = (Stack<Double>) pile.clone();
		undo.push(stack);
	}
}