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
public class ExpectingRightParOrEndException extends EvalException {

	private static final long serialVersionUID = -3110254399686504491L;
	String errorMessage = "ExpectingRightParOrEndException";

	public ExpectingRightParOrEndException() {
	}

	@Override
	public String toString() {
		return errorMessage;
	}

}