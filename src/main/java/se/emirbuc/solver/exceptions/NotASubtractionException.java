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
public class NotASubtractionException extends EvalException {

	private static final long serialVersionUID = 3493521503371431234L;

	public NotASubtractionException() {
	}

	@Override
	public String toString() {
		return NotASubtractionException.class.getSimpleName();
	}

}