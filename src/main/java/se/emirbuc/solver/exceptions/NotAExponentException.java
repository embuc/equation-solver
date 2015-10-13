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
public class NotAExponentException extends EvalException {

	private static final long serialVersionUID = -6202990517903769982L;

	public NotAExponentException() {
	}

	@Override
	public String toString() {
		return NotAExponentException.class.getSimpleName();
	}

}