package se.emirbuc.solver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import se.emirbuc.solver.exceptions.EvalException;
/**
 * The <code>Class SolverTest</code>.
 *
 * @author Emir Bucalovic (embuc)
 * @since 2015-okt-12
 */
public class SolverTest {

	@Test
	public void shouldHandleNullExpression() {
		Solver evaluator = new Solver(null);
		assertThrows(EvalException.class, evaluator::evaluate);
	}

	@Test
	public void shouldHandleSimpleAddition() throws Exception {
		Solver evaluator = new Solver("2+3");
		long result = evaluator.evaluate();
		assertEquals(5, result);
	}

	@Test
	public void shouldHandleSimpleAdditionWithSpaces() throws Exception {
		Solver evaluator = new Solver(" 2  +  3    ");
		long result = evaluator.evaluate();
		assertEquals(5, result);
	}

	@Test
	public void shouldHandleSimpleSubtraction() throws Exception {
		Solver evaluator = new Solver("7-3");
		long result = evaluator.evaluate();
		assertEquals(4, result);
	}

	@Test
	public void shouldHandleSimpleSubtractionWithSpaces() throws Exception {
		Solver evaluator = new Solver(" 7          - 3  ");
		long result = evaluator.evaluate();
		assertEquals(4, result);
	}

	@Test
	public void shouldHandleAdditionAndMultiplicationWithParentheses() throws Exception {
		Solver evaluator = new Solver("(2+3)*4");
		long result = evaluator.evaluate();
		assertEquals(20, result);
	}

	@Test
	public void shouldHandleSimpleAdditionAndMultiplication() throws Exception {
		Solver evaluator = new Solver("2+3*4");
		long result = evaluator.evaluate();
		assertEquals(14, result);
	}

	@Test
	public void shouldHandleAdditionAndDivision() throws Exception {
		Solver evaluator = new Solver("(10+30)/4");
		long result = evaluator.evaluate();
		assertEquals(10, result);
	}

	@Test
	public void shouldHandleAdditionAndDivision2() throws Exception {
		Solver evaluator = new Solver("(10+30)/4 +2");
		long result = evaluator.evaluate();
		assertEquals(12, result);
	}

	@Test
	public void shouldHandleAdditionAndDivision3() throws Exception {
		Solver evaluator = new Solver("3+ (10+30)/4");
		long result = evaluator.evaluate();
		assertEquals(13, result);
	}

	@Test
	public void shouldHandleAdditionAndDivision4() throws Exception {
		Solver evaluator = new Solver("3+ (10+30)/4 + 1");
		long result = evaluator.evaluate();
		assertEquals(14, result);
	}

	@Test
	public void shouldHandleLongExpression() throws Exception {
		Solver evaluator = new Solver("48+(10+30)/4+2*(4/2)-(12*2)");
		long result = evaluator.evaluate();
		assertEquals(38, result);
	}

	@Test
	public void shouldHandleLongExpression2() throws Exception {
		Solver evaluator = new Solver("48+(10+30)/4+2*4");
		long result = evaluator.evaluate();
		assertEquals(66, result);
	}

	@Test
	public void shouldHandleLongExpression3() throws Exception {
		Solver evaluator = new Solver("48+(10+30)/4+2*(4)");
		long result = evaluator.evaluate();
		assertEquals(66, result);
	}

	@Test
	public void shouldHandleLongExpression4() throws Exception {
		Solver evaluator = new Solver("((6*3)+4)*2+(7+3*9)+4!+6^2-19+2344/2");
		long result = evaluator.evaluate();
		assertEquals(1291, result);
	}

	@Test
	public void shouldHandleVeryLongExpression() throws Exception {
		Solver evaluator = new Solver(
				"((6*3)+4)*2+(7+3*9)+4!+6^2-19+2344/2 +6 -173+63*9-4^5 + 3*4 + 11  + 11 - 3*3 -2 " +
				"- 3^2 + 4! + 2^3 + 4^2 + 3^3 + 2^4 + 3^2 + 33/3 + 2^5 + 3! + 2^17 + 3^5 + 2^7 + " +
				" 3*12 + 2^1 + 3^3 + 2+15 + 3*14 + 2*16 + 3-16 + 2*18-12456 + 3*17 + 19 - 19 +  0");
		long result = evaluator.evaluate();
		assertEquals(120047, result);
	}

	@Test
	public void shouldHandleFactorial() throws Exception {
		Solver evaluator = new Solver("4!");
		long result = evaluator.evaluate();
		assertEquals(24, result);
	}

	@Test
	public void shouldHandleExponent() throws Exception {
		Solver evaluator = new Solver("4^2");
		long result = evaluator.evaluate();
		assertEquals(16, result);
	}
}