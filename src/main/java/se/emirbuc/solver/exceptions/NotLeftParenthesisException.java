package se.emirbuc.solver.exceptions;

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
@SuppressWarnings("javadoc")
public class NotLeftParenthesisException extends EvalException {
	private static final long serialVersionUID = 8137740842290051500L;

	public NotLeftParenthesisException() {
	}

	@Override
	public String toString() {
		return NotLeftParenthesisException.class.getSimpleName();
	}

}