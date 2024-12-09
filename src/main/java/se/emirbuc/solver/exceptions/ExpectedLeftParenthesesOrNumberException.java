package se.emirbuc.solver.exceptions;

/**
 * 
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
public class ExpectedLeftParenthesesOrNumberException extends EvalException {

	@Override
	public String toString() {
		return "Expected left parentheses or number!";
	}
}