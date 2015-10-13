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
public class Exponent {
	private char exp;

	public Exponent() {
		setExp(' ');
	}

	public char getExp() {
		return exp;
	}

	public void setExp(char exp) {
		this.exp = exp;
	}

}