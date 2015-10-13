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
@SuppressWarnings("javadoc")
public class ExpectedLeftParOrNumberException extends EvalException {
	private static final long serialVersionUID = -3244969682588097009L;
	String errorMessage = "ExpectedLeftParOrNumberException";

	public ExpectedLeftParOrNumberException() {
	}

	@Override
	public String toString() {
		return errorMessage;
	}
}