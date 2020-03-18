package RPN.CALCUL;

import java.util.HashMap;
import java.util.Map;

public class Interpreteur {
   
	private Map<String, Command> commands;
	
	private Interpreteur() {
		commands = new HashMap<String, Command>();
	}
	public void undo() {
		
	}
	
	public void quit() {
		System.exit(0);
		}
}
