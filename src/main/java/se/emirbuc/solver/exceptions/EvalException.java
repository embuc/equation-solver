package se.emirbuc.solver.exceptions;

/**
 * <p>
 * Description:T Lexical evaluator
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: Mah Datateknik
 * </p>
 * 
 * @author Emir Bucalovic
 * @version 1.0
 */
@SuppressWarnings("javadoc")
public class EvalException extends Exception {

	private static final long serialVersionUID = 7269772967859235325L;
	String error = "Evaluation Error!";

	public EvalException() {
	}

	@Override
	public String toString() {
		return error;
	}

}