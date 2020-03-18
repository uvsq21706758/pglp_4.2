package RPN.CALCUL;

public class Addition implements OperationCommand{
	public double apply(double operande_1, double operande_2)
	{
		return operande_1 + operande_2;
	}
}
