package RPN.CALCUL;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MoteurRPN {
	private Map<String, OperationCommand> operation;
	private Stack<Double> pile;
	private Undo undo;
	
	public MoteurRPN( Stack<Double> pile,Undo undo) {
		operation = new HashMap<String, OperationCommand>();
		this.pile = pile;
		this.undo=undo;
	}
	
	public void ajouterOperande(double operande)  
	{
		pile.push(operande);
	}
	public void addCommand( String name,  OperationCommand opcommand) {
		this.operation.put(name, opcommand);
	}
	public void executeCommand( String name) {
	
		if(operation.containsKey(name)) {
		
			double operande1 = pile.pop(); 
			double operande2 = pile.pop();
			try{
				pile.push(operation.get(name).eval(operande1, operande2));
			}
		
		catch (Exception e) 
		{
		    pile.push(operande1);
			pile.push(operande2);
			
		}
		
		}
	}
	public static MoteurRPN init( Stack<Double> pile, Undo undo) {
		MoteurRPN moteurrpn = new MoteurRPN(pile, undo);
		moteurrpn.addCommand("+", new Addition());
		moteurrpn.addCommand("-", new Soustraction());
		moteurrpn.addCommand("*", new Multiplication());
		moteurrpn.addCommand("/", new Division());
		return moteurrpn;
	}
	
	/*public String afficher()
	{
		String contenu = "";
		for(double pl : pile)
		{
			contenu += pl + " ";
		}
		return contenu;
	}*/
	
	public Stack<Double> getPile(){
		return this.pile;
	}
}
