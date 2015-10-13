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
public class Division {
	private char div;

	public Division() {
		setDiv(' ');
	}

	public char getDiv() {
		return div;
	}

	public void setDiv(char div) {
		this.div = div;
	}

}