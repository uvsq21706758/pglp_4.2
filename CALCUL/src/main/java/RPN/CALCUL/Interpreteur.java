package RPN.CALCUL;

import java.util.HashMap;
import java.util.Map;

public class Interpreteur {
   
	private Map<String, Command> command;
	
	private Interpreteur() {
		this.command = new HashMap<String, Command>();
	}
	
	public void addCommand(String name, Command command) {
		this.command.put(name, command);
	}
	public void executeCommand( String name) {
		if(command.containsKey(name)) {
			
			command.get(name).apply();
		}
	}
	
	public static Interpreteur init(Undo undo) {
	     Interpreteur interpreteur = new Interpreteur();
			
	     interpreteur.addCommand("undo", undo);
	     interpreteur.addCommand("quit", new Quit());
			
			return interpreteur;
			
		}
}
