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
	
	public void interUtilisateur() throws Exception {
		Scanner scanner = new Scanner(System.in);
		double op;
		String saisie = "";
		while(!saisie.equals("quit")) {
			try { 
				 op = scanner.nextDouble();
				moteur.ajouterOperande(op);
			}
			catch(java.util.InputMismatchException e) {
				saisie = scanner.nextLine();
				try {
					moteur.executeCommand(saisie);
				} catch (Exception m) {
					
						interpreteur.executeCommand(saisie);
					
				}
			}
			System.out.print( "\n"+moteur +"\n"); 
		}
		scanner.close();
		
	}
}