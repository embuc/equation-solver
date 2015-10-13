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
public class NotANumberException extends EvalException {
	private static final long serialVersionUID = 8139970761336226135L;

	public NotANumberException() {
	}

	@Override
	public String toString() {
		return NotANumberException.class.getSimpleName();
	}

}