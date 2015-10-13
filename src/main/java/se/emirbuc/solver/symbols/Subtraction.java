package se.emirbuc.solver.symbols;

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
public class Subtraction {
	private char minus;

	public Subtraction() {
		setMinus(' ');
	}

	public char getMinus() {
		return minus;
	}

	public void setMinus(char minus) {
		this.minus = minus;
	}

}