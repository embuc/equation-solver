package se.emirbuc.solver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * The <code>Class SolverTest</code>.
 *
 * @author Emir Bucalovic (embuc)
 * @since 2015-okt-12
 */
@SuppressWarnings("javadoc")
public class SolverTest {

	@Test
	public void testSimpleAdditionAndMultiplication() throws Exception {
		Solver evaluator = new Solver("(2+3)*4");
		long result = evaluator.start();
		assertEquals(20, result);
	}

	@Test
	public void testAdditionAndDivision() throws Exception {
		Solver evaluator = new Solver("(10+30)/4");
		long result = evaluator.start();
		assertEquals(10, result);
	}

	@Test
	public void testLongExpression() throws Exception {
		Solver evaluator = new Solver("48+(10+30)/4+2*(4/2)-(12*2)");
		long result = evaluator.start();
		System.out.println(result);
		assertEquals(48, result);
	}

}
