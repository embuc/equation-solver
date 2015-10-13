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
public class ExpectedPlusRightParOrEndException extends EvalException {

	private static final long serialVersionUID = -8200921700014339778L;
	String str = "ExpectedPlusRightParOrEndException";

	public ExpectedPlusRightParOrEndException() {
	}

	@Override
	public String toString() {
		return str;
	}

}