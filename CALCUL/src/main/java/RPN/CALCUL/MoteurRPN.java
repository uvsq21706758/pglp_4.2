package RPN.CALCUL;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MoteurRPN {
	private Map<String, OperationCommand> operations;
	private Stack<Double> pile;
	private Undo undo;
	
	private MoteurRPN(final Stack<Double> pile, final Undo undo) {
		operations = new HashMap<String, OperationCommand>();
		this.pile = pile;
		this.undo = undo;
	}
	
	public void addOperande(double d) {
		pile.push(d);
		undo.alertChange();
		
	}
	
	public String toString() {
		return pile.toString();
	}
	
	public int size() {
		return pile.size();
	}
	
	public void addCommand(final String name, final OperationCommand command) {
		this.operations.put(name, command);
	}
	
	public boolean executeCommand(final String name) throws Exception {
		if(operations.containsKey(name)) {
			if(pile.size() < 2) {
				System.err.println("nombre d'opérandes dans l'expression invalide");
				return false;
			}
			double operande2 = pile.pop();
			double operande1 = pile.pop();
			try {
				pile.push(operations.get(name).eval(operande1, operande2));
				undo.alertChange();
				return true;
			} catch (Exception e) {
				pile.push(operande1);
				pile.push(operande2);
				return false;
			}
		} else {
			//commande inconnu
			throw new Exception();
		}
	}
	
	public static MoteurRPN init(final Stack<Double> pile, final Undo u) {
		MoteurRPN m = new MoteurRPN(pile, u);
		m.addCommand("+", new Addition());
		m.addCommand("-", new Soustraction());
		m.addCommand("*", new Multiplication());
		m.addCommand("/", new Division());
		return m;
	}
}