package RPN.CALCUL;

public enum CalculatriceRPN {
	
	CALCUL(new SaisieRPN());
	
	private SaisieRPN saisierpn;
	
	CalculatriceRPN( SaisieRPN saisierpn)
	{
		this.saisierpn = saisierpn;
	}
	public void on() throws Exception
	{
		saisierpn.interUtilisateur();
	}
	
	public static void main(String[] args) throws Exception {
		
		CalculatriceRPN.CALCUL.on();
	}

}
