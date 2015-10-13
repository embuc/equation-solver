package se.emirbuc.solver.symbols;

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
public class Addition {
	private char plus;

	public Addition() {
		setPlus(' ');
	}

	public char getPlus() {
		return plus;
	}

	public void setPlus(char plus) {
		this.plus = plus;
	}

}