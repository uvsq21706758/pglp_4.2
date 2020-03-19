package RPN.CALCUL;

import java.util.Stack;

import java.util.Scanner;

public class SaisieRPN {
	private MoteurRPN moteur;
	private Interpreteur interprete;
	private Stack<Double> pile;
	private Scanner s;
	
	public SaisieRPN() {
		pile = new Stack<Double>();
		Undo u = new Undo(pile);
		u.alertChange();
		moteur = MoteurRPN.init(pile, u);
		interprete = Interpreteur.init(u);
	}
	
	/**
	 * Saisie morceau par morceau des éléments de calcul.
	 * s'arrete lorsque l'utilisateur entre "exit" ou en cas de saisie invalide.
	 * @return double correspondant au résultat du calcul
	 * @throws Exception 
	 */
	public void calcul() throws Exception {
		s = new Scanner(System.in);
		double d;
		boolean continuer = true;
		String line = "";
		System.out.print("Commandes autorisées : \n"
		+ "- un nombre dont la décimal est séparée d'une virgule\n"
		+ "- une opération parmis {+,-,*,/}\n"
		+ "- \"undo\" pour revenir en arrière d'une étape\n"
		+ "- \"quit\" pour terminer\n>");
		while(continuer) {
			try { //récupérer un nombre
				d = s.nextDouble();
				moteur.addOperande(d);
			}
			catch(java.util.InputMismatchException e) {
				line = s.nextLine();
				try { //récupérer une commande d'opération
					moteur.executeCommand(line);
				} catch (Exception m) {
					try { //récupérer une commande d'interpreteur
						interprete.executeCommand(line);
					} catch (Exception i) {
						System.err.println("Commande non autorisée ou non reconnue");
					}
				}
			}
			if(line.equalsIgnoreCase("quit")) {
				continuer = false;
			}
			System.out.print("\nExpression : " + moteur + "\n>"); //affichage de l'expression
		}
		s.close();
		//si on a pas un seul opérande, alors on ne peut pas déterminer le résultat
		if(moteur.size() != 1) {
			System.err.println("L'expression doit contenir 1 seul nombre");
			throw new Exception();
		}
		//retourne le résultat (vide la pile pour effectuer un nouveau calcul si voulu
		System.out.println("resultat : " + moteur);
	}
}