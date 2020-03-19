package RPN.CALCUL;

import java.util.Stack;

import java.util.Scanner;

public class SaisieRPN {
	private MoteurRPN moteur;
	private Interpreteur interpreteur;
	private Stack<Double> pile;
	
	public SaisieRPN() {
		pile = new Stack<Double>();
		Undo undo = new Undo(pile);
		moteur = MoteurRPN.init(pile, undo);
		interpreteur = Interpreteur.init(undo);
	}
	
	public void calcul() throws Exception {
		Scanner scanner = new Scanner(System.in);
		double d;
		boolean continuer = true;
		String line = "";
		while(continuer) {
			try { //récupérer un nombre
				d = scanner.nextDouble();
				moteur.addOperande(d);
			}
			catch(java.util.InputMismatchException e) {
				line = scanner.nextLine();
				try {
					moteur.executeCommand(line);
				} catch (Exception m) {
					
						interpreteur.executeCommand(line);
					
				}
			}
			if(line.equalsIgnoreCase("quit")) {
				continuer = false;
			}
			System.out.print("\n" + moteur + "\n>"); 
		}
		scanner.close();
		
	}
}