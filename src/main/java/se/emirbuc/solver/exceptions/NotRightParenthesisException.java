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
public class NotRightParenthesisException extends EvalException {

	private static final long serialVersionUID = -7144175190031572097L;

	public NotRightParenthesisException() {
	}

	@Override
	public String toString() {
		return NotRightParenthesisException.class.getSimpleName();
	}

}