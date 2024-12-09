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
public class EvalException extends Exception {

	private final String message;

	public EvalException() {
		this.message = "Evaluation failed!";
	}

	public EvalException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

}