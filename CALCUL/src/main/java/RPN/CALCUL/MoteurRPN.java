package RPN.CALCUL;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MoteurRPN {
	Map<String, OperationCommand> operations;
	private Stack<Double> pile;
	private Undo undo;
	
	private MoteurRPN( Stack<Double> pile, Undo undo) {
		operations = new HashMap<String, OperationCommand>();
		this.pile = pile;
		this.undo = undo;
	}
	
	public void ajouterOperande(double d) {
		pile.push(d);
		undo.changewarning();
		
	}
	
	public String toString() {
		return pile.toString();
	}

	public void addCommand(String name,OperationCommand command) {
		this.operations.put(name, command);
	}
	
	public boolean executeCommand(String name) throws Exception {
		if(operations.containsKey(name)) {
			if(pile.size() < 2) {
				System.err.println("nombre d'opérandes invalide,veuillez le rajouter");
				return false;
			}
			double operande_1 = pile.pop();
			double operande_2 = pile.pop();
			try {
				if(operande_2==0.0) {System.err.println("Erreur, division par zéro");
				return false;}
				pile.push(operations.get(name).eval(operande_1, operande_2));
				return true;
			} catch (Exception e) {
				pile.push(operande_1);
				pile.push(operande_2);
				return false;
			}
			
		} else {

			throw new Exception();
		}
	}
	
	public static MoteurRPN init(Stack<Double> pile,Undo undo) {
		MoteurRPN moteurrnp = new MoteurRPN(pile, undo);
		moteurrnp.addCommand("+", new Addition());
		moteurrnp.addCommand("-", new Soustraction());
		moteurrnp.addCommand("*", new Multiplication());
		moteurrnp.addCommand("/", new Division());
		return moteurrnp;
	
}
}