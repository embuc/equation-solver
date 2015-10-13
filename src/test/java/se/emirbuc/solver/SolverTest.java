package se.emirbuc.solver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import se.emirbuc.solver.exceptions.EvalException;

/**
 * The <code>Class SolverTest</code>.
 *
 * @author Emir Bucalovic (embuc)
 * @since 2015-okt-12
 */
@SuppressWarnings("javadoc")
public class SolverTest {

	@Test(expected = EvalException.class)
	public void testNullExpression() throws Exception {
		Solver evaluator = new Solver(null);
		evaluator.evaluate();
	}

	@Test
	public void testNumberMatcherSimple() {
		Solver evaluator = new Solver("");
		String number = evaluator.extractNumber("4+2");
		assertEquals("4", number);
		number = evaluator.extractNumber("14-2");
		assertEquals("14", number);
	}

	@Test
	public void testNumberMatcherNotStartOfString() {
		Solver evaluator = new Solver("");
		String number = evaluator.extractNumber("4+2");
		assertEquals("4", number);
		number = evaluator.extractNumber("+4+2");
		assertEquals(null, number);
	}

	@Test
	public void testNumberMatcherNegativeNumber() {
		Solver evaluator = new Solver("");
		String number = evaluator.extractNumber("-4+2");
		assertEquals("-4", number);
		number = evaluator.extractNumber("(-4+2");
		assertEquals(null, number);
		number = evaluator.extractNumber("()^*-4+2");
		assertEquals(null, number);
		number = evaluator.extractNumber("---4+2");
		assertEquals(null, number);
	}

	@Test
	public void testSimpleAddition() throws Exception {
		Solver evaluator = new Solver("2+3");
		long result = evaluator.evaluate();
		assertEquals(5, result);
	}

	@Test
	public void testSimpleAdditionWithSpaces() throws Exception {
		Solver evaluator = new Solver(" 2  +  3    ");
		long result = evaluator.evaluate();
		assertEquals(5, result);
	}

	@Test
	public void testSimpleSubtraction() throws Exception {
		Solver evaluator = new Solver("7-3");
		long result = evaluator.evaluate();
		assertEquals(4, result);
	}

	@Test
	public void testSimpleSubtractionWiothSpaces() throws Exception {
		Solver evaluator = new Solver(" 7          - 3  ");
		long result = evaluator.evaluate();
		assertEquals(4, result);
	}

	@Test
	public void testSimpleAdditionAndMultiplication() throws Exception {
		Solver evaluator = new Solver("(2+3)*4");
		long result = evaluator.evaluate();
		assertEquals(20, result);
	}

	@Test
	public void testAdditionAndDivision() throws Exception {
		Solver evaluator = new Solver("(10+30)/4");
		long result = evaluator.evaluate();
		assertEquals(10, result);
	}

	@Test
	public void testAdditionAndDivision2() throws Exception {
		Solver evaluator = new Solver("(10+30)/4 +2");
		long result = evaluator.evaluate();
		assertEquals(12, result);
	}

	@Test
	public void testAdditionAndDivision3() throws Exception {
		Solver evaluator = new Solver("3+ (10+30)/4");
		long result = evaluator.evaluate();
		assertEquals(13, result);
	}

	@Test
	public void testAdditionAndDivision4() throws Exception {
		Solver evaluator = new Solver("3+ (10+30)/4 + 1");
		long result = evaluator.evaluate();
		assertEquals(14, result);
	}

	@Test
	public void testLongExpression() throws Exception {
		Solver evaluator = new Solver("48+(10+30)/4+2*(4/2)-(12*2)");
		long result = evaluator.evaluate();
		assertEquals(38, result);
	}

	@Test
	public void testLongExpression2() throws Exception {
		Solver evaluator = new Solver("48+(10+30)/4+2*4");
		long result = evaluator.evaluate();
		assertEquals(66, result);
	}

	@Test
	public void testLongExpression3() throws Exception {
		Solver evaluator = new Solver("48+(10+30)/4+2*(4)");
		long result = evaluator.evaluate();
		assertEquals(66, result);
	}

}
