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
public class NotAnAdditionException extends EvalException {

	private static final long serialVersionUID = -7655798033898933347L;

	public NotAnAdditionException() {
	}

	@Override
	public String toString() {
		return NotAnAdditionException.class.getSimpleName();
	}

}