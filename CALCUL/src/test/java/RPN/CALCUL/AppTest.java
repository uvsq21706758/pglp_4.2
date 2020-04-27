package RPN.CALCUL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Stack;

import org.junit.Test;

public class AppTest {
	
	@Test
	public void testInterpreteur()
	{
		Interpreteur it = new Interpreteur();
		assertNotNull(it.command);
	}
	
	@Test
	public void interpreteur_inittest()
	{
		Stack<Double> pile = null;
		Undo u=new Undo(pile);
		Interpreteur it = Interpreteur.init(u);
		assertNotNull(it.command);
	}
	
	@Test
	public void moteurrpn_initTest() throws Exception
	{
		MoteurRPN motrpn = MoteurRPN.init(null, null);
		assertNotNull(motrpn.operations);
	}
	
	@Test
	public void ajouterOperandeTest()
	{
		Stack<Double> pile = new Stack<Double>();
		Undo u=new Undo(pile);
		MoteurRPN motrpn = MoteurRPN.init(pile, u);
		motrpn.ajouterOperande(1.0);
		Stack<Double> p = new Stack<Double>();
		p.push(1.0);
		assertEquals(p.toString(),motrpn.toString());
	
	}
	@Test
	public void undoTest() throws Exception
	{
		Stack<Double> pile = new Stack<Double>();
		Undo u=new Undo(pile);
		MoteurRPN motrpn = MoteurRPN.init(pile, u);
		motrpn.ajouterOperande(1.0);
		motrpn.ajouterOperande(1.0);
		motrpn.executeCommand("undo");
		Stack<Double> p = new Stack<Double>();
		p.push(1.0);
		assertEquals(p.toString(),motrpn.toString());
	}
	
}
