package se.emirbuc.solver;

import static org.junit.Assert.*;

import org.junit.Test;


public class HuvudTest {

	@Test
	public void testSimpleAdditionAndMultiplication() throws Exception {
		Huvud evaluator = new Huvud("(2+3)*4");
		long result = evaluator.start();
		assertEquals(20, result);
	}
	
	@Test
	public void testAdditionAndDivision() throws Exception {
		Huvud evaluator = new Huvud("(10+30)/4");
		long result = evaluator.start();
		assertEquals(10, result);
	}

	@Test
	public void testLongExpression() throws Exception {
		Huvud evaluator = new Huvud("48+(10+30)/4+2*(4/2)-(12*2)");
		long result = evaluator.start();
		System.out.println(result);
		assertEquals(10, result);
	}

}
