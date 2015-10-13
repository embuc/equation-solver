package se.emirbuc.solver;

import se.emirbuc.solver.exceptions.EvalException;
import se.emirbuc.solver.exceptions.ExpectedLeftParOrNumberException;
import se.emirbuc.solver.exceptions.ExpectedPlusRightParOrEndException;
import se.emirbuc.solver.exceptions.ExpectingRightParOrEndException;
import se.emirbuc.solver.exceptions.NotADivisionException;
import se.emirbuc.solver.exceptions.NotAExponentException;
import se.emirbuc.solver.exceptions.NotAMultiplicationException;
import se.emirbuc.solver.exceptions.NotASubtractionException;
import se.emirbuc.solver.exceptions.NotRightParenthesisException;
import se.emirbuc.solver.symbols.Addition;
import se.emirbuc.solver.symbols.Division;
import se.emirbuc.solver.symbols.Exponent;
import se.emirbuc.solver.symbols.LeftParenthesis;
import se.emirbuc.solver.symbols.Multiplication;
import se.emirbuc.solver.symbols.Number;
import se.emirbuc.solver.symbols.RightParenthesis;
import se.emirbuc.solver.symbols.Subtraction;

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
		this.expression = expression;
	}

	private int stringToInt(String s) {
		int decim = 1, num = 0, current;
		for (int i = s.length() - 1; i >= 0; i--) {
			current = s.charAt(i) - ('0');
			num += current * decim;
			decim *= 10;
		}
		return num;
	}

	private Number getNumber() {
		Number number = new Number();
		boolean negative = false;
		int first, last;
		String partialString;

		fastForwardIndexToNextSymbol();
		if (expression.charAt(index) == '-') {
			negative = true; // kom ih�g att det �r negativt number och flytta indexet
			index++;
		} else if (expression.charAt(index) == '+')
			index++; // endast flytta indexet ett steg �t h�ger
		fastForwardIndexToNextSymbol();
		first = index; // kom ih�g var f�rsta siffran b�rjar i delstr�ngen
		while (((expression.charAt(index) == '0') | (expression.charAt(index) == '1') | (expression.charAt(index) == '2')
				| (expression.charAt(index) == '3') | (expression.charAt(index) == '4') | (expression.charAt(index) == '5')
				| (expression.charAt(index) == '6') | (expression.charAt(index) == '7') | (expression.charAt(index) == '8')
				| (expression.charAt(index) == '9')) && (!noMoreInput())) {
			index++;
		}
		last = index; // kom ih�g var siffran slutar
		if (Character.isDigit(expression.charAt(index))) {
			partialString = expression.substring(first, last + 1);
		}
		else {
			partialString = expression.substring(first, last);
		}
		number.setNumber(this.stringToInt(partialString));
		if (negative) {
			number.invertTheSignOfNumber();
		}
		return number;
	}

	private Addition getPlus() {
		Addition pp = new Addition();
		fastForwardIndexToNextSymbol();
		pp.setPlus(expression.charAt(index));
		index++;
		return pp;
	}

	private Subtraction getMinus() {
		Subtraction minus = new Subtraction();
		fastForwardIndexToNextSymbol();
		minus.setMinus(expression.charAt(index));
		index++;
		return minus;
	}

	private Multiplication getMultiplication() {
		Multiplication mult = new Multiplication();
		fastForwardIndexToNextSymbol();
		mult.setMult(expression.charAt(index));
		index++;
		return mult;
	}

	private Division getDivision() {
		Division div = new Division();
		fastForwardIndexToNextSymbol();
		div.setDiv(expression.charAt(index));
		index++;
		return div;
	}

	/**
	 * Gets the exponent e.g. '^'.
	 *
	 * @return the exponent
	 */
	private Exponent getExponent() {
		Exponent exp = new Exponent();
		fastForwardIndexToNextSymbol();
		exp.setExp(expression.charAt(index));
		index++;
		return exp;
	}

	private void getFactorial() {
		fastForwardIndexToNextSymbol();
		index++;
	}

	private LeftParenthesis getLeftParenthesis() {
		LeftParenthesis leftp = new LeftParenthesis();
		fastForwardIndexToNextSymbol();
		leftp.setLpar(expression.charAt(index));
		index++;
		return leftp;
	}

	private void fastForwardIndexToNextSymbol() {
		// ignore blanks
		while ((expression.charAt(index) == ' ') && (!noMoreInput())) {
			index++;
		}
	}

	private RightParenthesis getRightParenthesis() {
		RightParenthesis rpar = new RightParenthesis();
		while ((expression.charAt(index) == ' ') && (!noMoreInput())) { // ignorera blank{
			index++;
		}
		rpar.setRpar(expression.charAt(index));
		if (!noMoreInput())
			index++;
		return rpar;
	}

	/**
	 * Checks if next symbol is number.
	 *
	 * @return true, if is number
	 */
	private boolean isNumber() {
		int local = index;
		try {
			char current = expression.charAt(local);
			while ((expression.charAt(index) == ' ') && (!noMoreInput())) {
				// skip blanks
				index++;
			}
			// hantera om siffran b�rjar med prefix + V -
			if ((expression.charAt(index) == '+' | expression.charAt(index) == '-') && !noMoreInput()) {
				index++;
				fastForwardIndexToNextSymbol();
			}
			// testa h�r isSiffra()
			while (((expression.charAt(index) == '0') | (expression.charAt(index) == '1') | (expression.charAt(index) == '2')
					| (expression.charAt(index) == '3') | (expression.charAt(index) == '4') | (expression.charAt(index) == '5')
					| (expression.charAt(index) == '6') | (expression.charAt(index) == '7') | (expression.charAt(index) == '8')
					| (expression.charAt(index) == '9')) && (!noMoreInput())) {
				index++;
			}
			if ((!(index == 0))) // om det finns tecken efter typ parantes eller blank
				if (noMoreInput() && Character.isDigit(expression.charAt(index)))
					current = expression.charAt(index); // om det �r the very sista tecknet
				else
					current = expression.charAt(index - 1);
			else
				current = expression.charAt(index); // om det �r the very sista tecknet
			index = local; // �terst�ll indexet
			if ((current == '0') | (current == '1') | (current == '2') | (current == '3') | (current == '4') | (current == '5') | (current == '6')
					| (current == '7') | (current == '8') | (current == '9'))
				return true; // om sista tecknet i travaseringen verkligen �r en siffra d� returnera true
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return false; // annars returnera false
	}

	/**
	 * Checks if next symbol is addition.
	 *
	 * @return true, if is addition
	 */
	private boolean isAddition() {
		int local = index;
		try {
			while ((expression.charAt(local) == ' ') && (!noMoreInput())) // ignorera blank
				local++;
			if (expression.charAt(local) == '+')
				return true;
		} catch (StringIndexOutOfBoundsException e) {
		}
		return false;
	}

	/**
	 * Checks if next symbol is subtraction.
	 *
	 * @return true, if is subtraction
	 */
	public boolean isSubtraction() {
		int local = index;
		try {
			while ((expression.charAt(local) == ' ') && (!noMoreInput())) // ignorera blank
				local++;
			if (expression.charAt(local) == '-')
				return true;
		} catch (StringIndexOutOfBoundsException e) {
		}
		return false;
	}

	/**
	 * Checks if is next symbol multiplication.
	 *
	 * @return true, if is multiplication
	 */
	private boolean isMultiplication() {
		int local = index;
		try {
			while ((!noMoreInput()) && (expression.charAt(local) == ' ')) // ignorera blank
				local++;
			if (expression.charAt(local) == '*')
				return true;
		} catch (StringIndexOutOfBoundsException e) {
		}
		return false;
	}

	/**
	 * Checks if next symbol is division.
	 *
	 * @return true, if is division
	 */
	private boolean isDivision() {
		int local = index;
		try {
			while ((!noMoreInput()) && (expression.charAt(local) == ' ')) // ignorera blank
				local++;
			if (expression.charAt(local) == '/')
				return true;
		} catch (StringIndexOutOfBoundsException e) {
		}
		return false;
	}

	/**
	 * Checks if next symbol is exponent.
	 *
	 * @return true, if is exponent
	 */
	private boolean isExponent() {
		int local = index;
		try {
			while ((!noMoreInput()) && (expression.charAt(local) == ' ')) // ignorera blank
				local++;
			if (expression.charAt(local) == '^')
				return true;
		} catch (StringIndexOutOfBoundsException e) {
		}
		return false;
	}

	/**
	 * Checks if next symbol is factorial.
	 *
	 * @return true, if is factorial
	 */
	private boolean isFactorial() {
		int local = index;
		try {
			while ((!noMoreInput()) && (expression.charAt(local) == ' ')) // ignorera blank
				local++;
			if (expression.charAt(local) == '!')
				return true;
		} catch (StringIndexOutOfBoundsException e) {
		}
		return false;
	}

	/**
	 * Checks if next symbol is left parenthesis.
	 *
	 * @return true, if is left parenthesis
	 */
	private boolean isLeftParenthesis() {
		int local = index;
		try {
			while ((expression.charAt(local) == ' ') && (!noMoreInput())) // ignorera blank
				local++;
			if (expression.charAt(local) == '(')
				return true;
		} catch (StringIndexOutOfBoundsException e) {
		}
		return false;
	}

	/**
	 * Checks if next symbol is right parenthesis.
	 *
	 * @return true, if is right parenthesis
	 */
	private boolean isRightParenthesis() {
		int local = index;
		try {
			while ((expression.charAt(local) == ' ') && (!noMoreInput())) // ignorera blank
				local++;
			if (expression.charAt(local) == ')')
				return true;
		} catch (StringIndexOutOfBoundsException e) {
		}
		return false;
	}

	/**
	 * No more input checks if there is anything left in the string ('looks ahead').
	 *
	 * @return true, if successful
	 */
	private boolean noMoreInput() {
		if ((index + 1) >= expression.length())
			return true;
		return false;
	}

	/**
	 * Grammar rule "Start".
	 *
	 * @return the long
	 * @throws EvalException
	 *             the eval exception
	 */
	public long start() throws EvalException {
		long i;
		i = t();
		if (isAddition())
			return x() + i;
		else if (isSubtraction())
			return i - w();
		else if (!noMoreInput() && !isRightParenthesis())
			throw new ExpectingRightParOrEndException();
		return i;
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
		getPlus();
		i = t(); // b�rja om dvs s�ka v�nster associativa...
		if (isAddition())
			return x() + i;
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
		try {
			getMinus();
			i = t();
			if (isSubtraction())
				return i + w();// en fuling + :)
			return i;
		} catch (NotASubtractionException e) {
			throw e;
		}
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
		try {
			getExponent();
			i = f();
			if (isExponent())
				return (long) Math.pow(i, e());
		} catch (NotAExponentException e) {
			throw e;
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
		getFactorial();
		for (; i > 1; i--)
			res *= i;
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
		if (isFactorial()) {
			i = cF(i);
		}
		if (isExponent())
			i = (long) Math.pow(i, e());
		if (isMultiplication())
			return y() * i;
		else if (isDivision())
			try {
				return i / d(); // h�r kan exception h�nda - division med noll t. ex.
			} catch (ArithmeticException e) {
				System.out.println("ArithmeticException: " + e.getMessage());
			}
		else if ((!noMoreInput()) && (!isRightParenthesis()) && (!isAddition()) && (!isSubtraction()))
			throw new ExpectedPlusRightParOrEndException();
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
		try {
			getMultiplication();
			i = t();
			if (isMultiplication())
				return y() * i;
		} catch (NotAMultiplicationException e) {
			throw e;
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
		try {
			getDivision();
			i = t();
			if (isDivision())
				return i / d();
		} catch (NotADivisionException e) {
			throw e;
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
			try {
				getLeftParenthesis();
				i = start();
				getRightParenthesis();
			} catch (NotRightParenthesisException x) {
				throw x;
			}
		} else if (isNumber()) {
			i = getNumber().getNumber();
		} else
			throw new ExpectedLeftParOrNumberException();
		return i;
	}

}