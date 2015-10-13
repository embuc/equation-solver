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
public class NotAMultiplicationException extends EvalException {
	private static final long serialVersionUID = 3272228243348048773L;

	public NotAMultiplicationException() {
	}

	@Override
	public String toString() {
		return NotAMultiplicationException.class.getSimpleName();
	}

}