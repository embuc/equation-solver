package se.emirbuc.solver;

import se.emirbuc.solver.exceptions.EvalException;
import se.emirbuc.solver.exceptions.ExpectedLeftParenthesesOrNumberException;
import se.emirbuc.solver.exceptions.ExpectingRightParenthesesOrEndException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Description:T Lexical evaluator
 * </p>
 * <p>
 * Represents an arithmetic expression solver with operator precedence. This class evaluates mathematical expressions following standard rules.
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
	/** Current index being evaluated in the expression string. */
	private int currentIndex;

	/**
	 * Initializes the solver with the given expression.
	 *
	 * @param expression the mathematical expression to evaluate.
	 */
	public Solver(String expression) {
		if (expression != null) {
			this.expression = expression.replace(" ", "");
		}
	}

	/**
	 * Evaluates the provided mathematical expression and returns the result.
	 *
	 * @return the result of the evaluated expression.
	 * @throws EvalException if the expression is invalid.
	 */
	public long evaluate() throws EvalException {
		currentIndex = 0;
		return parseExpression();
	}

	/**
	 * Parses and evaluates the top-level expression (handles addition and subtraction).
	 *
	 * @return the result of the top-level expression.
	 * @throws EvalException if the expression is invalid.
	 */
	private long parseExpression() throws EvalException {
		if (expression == null) {
			throw new EvalException("Cannot evaluate an empty expression!");
		}

		long result = parseTerm();

		while (hasMoreCharacters()) {
			if (isAdditionOperator()) {
				currentIndex++;
				result += parseTerm();
			} else if (isSubtractionOperator()) {
				currentIndex++;
				result -= parseTerm();
			} else {
				break;
			}
		}

		return result;
	}

	/**
	 * Parses and evaluates terms in the expression (handles multiplication and division).
	 *
	 * @return the result of the term evaluation.
	 * @throws EvalException if the term is invalid.
	 */
	private long parseTerm() throws EvalException {
		long result = parseFactor();

		while (hasMoreCharacters()) {
			if (isMultiplicationOperator()) {
				currentIndex++;
				result *= parseFactor();
			} else if (isDivisionOperator()) {
				currentIndex++;
				long divisor = parseFactor();
				if (divisor == 0) {
					throw new ArithmeticException("Division by zero!");
				}
				result /= divisor;
			} else {
				break;
			}
		}

		return result;
	}

	/**
	 * Parses and evaluates factors in the expression (handles numbers, parentheses, factorials, and exponentiation).
	 *
	 * @return the result of the factor evaluation.
	 * @throws EvalException if the factor is invalid.
	 */
	private long parseFactor() throws EvalException {
		long result;

		if (isLeftParenthesis()) {
			currentIndex++;
			result = parseExpression(); // Recursively parse the expression inside parentheses
			if (!isRightParenthesis()) {
				throw new ExpectingRightParenthesesOrEndException();
			}
			currentIndex++;
		} else if (isNextCharacterNumber()) {
			result = extractNumber();
		} else {
			throw new ExpectedLeftParenthesesOrNumberException();
		}

		// Handle factorial
		if (hasMoreCharacters() && isFactorialOperator()) {
			currentIndex++;
			result = calculateFactorial(result);
		}

		// Handle exponentiation
		if (hasMoreCharacters() && isExponentiationOperator()) {
			currentIndex++;
			result = calculateExponentiation(result, parseFactor());
		}

		return result;
	}

	/**
	 * Calculates the factorial of a number.
	 *
	 * @param number the number to calculate the factorial for.
	 * @return the factorial of the number.
	 */
	private long calculateFactorial(long number) {
		long result = 1;
		for (long i = number; i > 1; i--) {
			result *= i;
		}
		return result;
	}

	/**
	 * Calculates the result of raising a base number to an exponent.
	 *
	 * @param base     the base number.
	 * @param exponent the exponent.
	 * @return the result of base raised to the power of exponent.
	 */
	private long calculateExponentiation(long base, long exponent) {
		return (long) Math.pow(base, exponent);
	}

	/**
	 * Extracts a number from the current position in the expression string.
	 *
	 * @return the extracted number.
	 */
	private int extractNumber() {
		String input = expression.substring(currentIndex);
		String extractedNumber = extractNumberFromInput(input);
		currentIndex += extractedNumber.length();
		return Integer.parseInt(extractedNumber);
	}

	/**
	 * Extracts the numeric portion of a string starting at the beginning.
	 *
	 * @param input the input string to extract the number from.
	 * @return the extracted numeric string.
	 */
	private String extractNumberFromInput(String input) {
		Pattern pattern = Pattern.compile("^-?\\d+");
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return matcher.group();
		}
		throw new RuntimeException("Invalid number format!");
	}

	/**
	 * Checks if the next character in the expression is a number.
	 *
	 * @return true if the next character is a number, false otherwise.
	 */
	private boolean isNextCharacterNumber() {
		Pattern pattern = Pattern.compile("^-?\\d+");
		Matcher matcher = pattern.matcher(expression.substring(currentIndex));
		return matcher.find();
	}

	/**
	 * Checks if the current character is an addition operator ('+').
	 *
	 * @return true if the current character is '+', false otherwise.
	 */
	private boolean isAdditionOperator() {
		return expression.charAt(currentIndex) == '+';
	}

	/**
	 * Checks if the current character is a subtraction operator ('-').
	 *
	 * @return true if the current character is '-', false otherwise.
	 */
	private boolean isSubtractionOperator() {
		return expression.charAt(currentIndex) == '-';
	}

	/**
	 * Checks if the current character is a multiplication operator ('*').
	 *
	 * @return true if the current character is '*', false otherwise.
	 */
	private boolean isMultiplicationOperator() {
		return expression.charAt(currentIndex) == '*';
	}

	/**
	 * Checks if the current character is a division operator ('/').
	 *
	 * @return true if the current character is '/', false otherwise.
	 */
	private boolean isDivisionOperator() {
		return expression.charAt(currentIndex) == '/';
	}

	/**
	 * Checks if the current character is an exponentiation operator ('^').
	 *
	 * @return true if the current character is '^', false otherwise.
	 */
	private boolean isExponentiationOperator() {
		return expression.charAt(currentIndex) == '^';
	}

	/**
	 * Checks if the current character is a factorial operator ('!').
	 *
	 * @return true if the current character is '!', false otherwise.
	 */
	private boolean isFactorialOperator() {
		return expression.charAt(currentIndex) == '!';
	}

	/**
	 * Checks if the current character is a left parenthesis ('(').
	 *
	 * @return true if the current character is '(', false otherwise.
	 */
	private boolean isLeftParenthesis() {
		return expression.charAt(currentIndex) == '(';
	}

	/**
	 * Checks if the current character is a right parenthesis (')').
	 *
	 * @return true if the current character is ')', false otherwise.
	 */
	private boolean isRightParenthesis() {
		return expression.charAt(currentIndex) == ')';
	}

	/**
	 * Checks if there are more characters left in the expression to parse.
	 *
	 * @return true if there are more characters, false otherwise.
	 */
	private boolean hasMoreCharacters() {
		return currentIndex < expression.length();
	}
}
