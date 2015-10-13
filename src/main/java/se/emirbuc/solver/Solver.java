package se.emirbuc.solver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.emirbuc.solver.exceptions.EvalException;
import se.emirbuc.solver.exceptions.ExpectedLeftParOrNumberException;
import se.emirbuc.solver.exceptions.ExpectedPlusRightParOrEndException;
import se.emirbuc.solver.exceptions.ExpectingRightParOrEndException;

/**
 * <p>
 * Description:T Lexical evaluator
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * 
 * @author Emir Bucalovic
 * @version 1.0
 */
public class Solver {

	private String expression;
	/** index in expression */
	private int index;

	/**
	 * Instantiates a new solver.
	 *
	 * @param expression
	 *            the expression to be evaluated.
	 */
	public Solver(String expression) {
		if (expression != null) {
			this.expression = expression.replace(" ", "");
		}
	}

	/**
	 * Checks if next symbol is addition.
	 *
	 * @return true, if is addition
	 */
	private boolean isAddition() {
		if (expression.charAt(index) == '+') {
			return true;
		}
		return false;
	}

	/**
	 * Checks if next symbol is subtraction.
	 *
	 * @return true, if is subtraction
	 */
	public boolean isSubtraction() {
		if (expression.charAt(index) == '-') {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is next symbol multiplication.
	 *
	 * @return true, if is multiplication
	 */
	private boolean isMultiplication() {
		if (expression.charAt(index) == '*') {
			return true;
		}
		return false;
	}

	/**
	 * Checks if next symbol is division.
	 *
	 * @return true, if is division
	 */
	private boolean isDivision() {
		if (expression.charAt(index) == '/') {
			return true;
		}
		return false;
	}

	/**
	 * Checks if next symbol is exponent.
	 *
	 * @return true, if is exponent
	 */
	private boolean isExponent() {
		if (expression.charAt(index) == '^') {
			return true;
		}
		return false;
	}

	/**
	 * Checks if next symbol is factorial.
	 *
	 * @return true, if is factorial
	 */
	private boolean isFactorial() {
		if (expression.charAt(index) == '!') {
			return true;
		}
		return false;
	}

	/**
	 * Checks if next symbol is left parenthesis.
	 *
	 * @return true, if is left parenthesis
	 */
	private boolean isLeftParenthesis() {
		if (expression.charAt(index) == '(') {
			return true;
		}
		return false;
	}

	/**
	 * Checks if next symbol is right parenthesis.
	 *
	 * @return true, if is right parenthesis
	 */
	private boolean isRightParenthesis() {
		if (expression.charAt(index) == ')') {
			return true;
		}
		return false;
	}

	/**
	 * Checks if there is anything left in the string ('looks ahead').
	 *
	 * @return true, if possible to 'pop' more characters
	 */
	private boolean hasMoreCharacters() {
		if ((index + 1) >= expression.length()) {
			return false;
		}
		return true;
	}

	/**
	 * Evaluate.
	 *
	 * @return the long
	 * @throws EvalException
	 *             the eval exception
	 */
	public long evaluate() throws EvalException {
		index = 0;
		return start();
	}

	/**
	 * Grammar rule "Start".
	 *
	 * @return the long
	 * @throws EvalException
	 *             the eval exception
	 */
	public long start() throws EvalException {
		if (expression == null) {
			throw new EvalException("Can not evaluate empty expression!");
		}
		long result;
		result = t();
		if (hasMoreCharacters() && isAddition()) {
			return x() + result;
		} else if (hasMoreCharacters() && isSubtraction()) {
			return result - w();
		} else if (hasMoreCharacters() && !isRightParenthesis()) {
			throw new ExpectingRightParOrEndException();
		}
		return result;
	}

	/**
	 * Grammar rule "X".
	 * 
	 * @return the long
	 * @throws EvalException
	 *             the eval exception
	 */
	private long x() throws EvalException {
		long i;
		index++;
		// start again -> left - associative rule
		i = t();
		if (hasMoreCharacters() && isAddition()) {
			return x() + i;
		}
		return i;
	}

	/**
	 * Grammar rule "W".
	 *
	 * @return the long
	 * @throws EvalException
	 *             the eval exception
	 */
	private long w() throws EvalException {
		long i = 0;
		index++;
		i = t();
		if (hasMoreCharacters() && isSubtraction()) {
			return i + w();// en fuling + :)
		}
		return i;
	}

	/**
	 * Grammar rule "E".
	 *
	 * @return the long
	 * @throws EvalException
	 *             the eval exception
	 */
	private long e() throws EvalException {
		long i = 0;
		index++;
		i = f();
		if (hasMoreCharacters() && isExponent()) {
			return (long) Math.pow(i, e());
		}
		return i;
	}

	/**
	 * Grammar rule "cf".
	 *
	 * @param i
	 *            the i
	 * @return the long
	 */
	private long cF(long i) {
		long res = 1;
		index++;
		for (; i > 1; i--) {
			res *= i;
		}
		return res;
	}

	/**
	 * Grammar rule T.
	 *
	 * @return the long
	 * @throws EvalException
	 *             the eval exception
	 */
	private long t() throws EvalException {
		long i;
		i = f();
		if (hasMoreCharacters() && isFactorial()) {
			i = cF(i);
		}
		if (hasMoreCharacters() && isExponent()) {
			i = (long) Math.pow(i, e());
		}
		if (hasMoreCharacters() && isMultiplication()) {
			return y() * i;
		} else if (hasMoreCharacters() && isDivision()) {
			return i / d(); // TODO division with zero!
		} else if (hasMoreCharacters() && !isRightParenthesis() && !isAddition() && !isSubtraction()) {
			throw new ExpectedPlusRightParOrEndException();
		}
		return i;
	}

	/**
	 * Grammar rule Y.
	 *
	 * @return the long
	 * @throws EvalException
	 *             the eval exception
	 */
	private long y() throws EvalException {
		long i = 0;
		index++;
		i = t();
		if (hasMoreCharacters() && isMultiplication()) {
			return y() * i;
		}
		return i;
	}

	/**
	 * Grammar rule D.
	 *
	 * @return the long
	 * @throws EvalException
	 *             the eval exception
	 */
	private long d() throws EvalException {
		long i = 0;
		index++;
		i = t();
		if (hasMoreCharacters() && isDivision()) {
			return i / d();
		}
		return i;
	}

	/**
	 * Grammar rule "f"
	 * 
	 * @return a number or partial result (of expression between parenthesis)
	 * @throws EvalException
	 */
	private long f() throws EvalException {
		long i = 0;
		if (isLeftParenthesis()) {
			index++;
			i = start();
			index++;
		} else if (isNextSymbolNumber()) {
			i = getNumber();
		} else {
			throw new ExpectedLeftParOrNumberException();
		}
		return i;
	}

	private int getNumber() {
		String input = expression.substring(index);
		String extractedNumber = extractNumber(input);
		index += extractedNumber.length();
		return Integer.valueOf(extractedNumber).intValue();
	}

	protected String extractNumber(String input) {
		Pattern p = Pattern.compile("^-?\\d+");
		Matcher m = p.matcher(input);
		if (m.find()) {
			return m.group();
		}
		return null;
	}

	private boolean isNextSymbolNumber() {
		Pattern p = Pattern.compile("^-?\\d+");
		Matcher m = p.matcher(expression.substring(index));
		return m.find();
	}
}