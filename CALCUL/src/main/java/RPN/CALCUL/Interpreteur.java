package RPN.CALCUL;

import java.util.HashMap;
import java.util.Map;

public class Interpreteur {
   
	private Map<String, Command> commands;
	
	private Interpreteur() {
		this.commands = new HashMap<String, Command>();
	}
	
	public void addCommand(String name, Command command) {
		this.commands.put(name, command);
	}
	public void executeCommand( String name) {
		if(commands.containsKey(name)) {
			
				commands.get(name).apply();
		}
	}
	
	public static Interpreteur init() {
	     Interpreteur itrp = new Interpreteur();
			
	     itrp.addCommand("undo", new Undo());
	     itrp.addCommand("quit", new Quit());
			
			return itrp;
			
		}
}
