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
public class NotADivisionException extends EvalException {

	private static final long serialVersionUID = -3981294793505164522L;

	@SuppressWarnings("javadoc")
	public NotADivisionException() {
	}

	@Override
	public String toString() {
		return NotADivisionException.class.getSimpleName();
	}

}