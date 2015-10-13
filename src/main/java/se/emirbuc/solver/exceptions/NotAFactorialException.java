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
public class NotAFactorialException extends EvalException {

	private static final long serialVersionUID = -4474417678085265576L;

	public NotAFactorialException() {
	}

	@Override
	public String toString() {
		return NotAFactorialException.class.getSimpleName();
	}

}