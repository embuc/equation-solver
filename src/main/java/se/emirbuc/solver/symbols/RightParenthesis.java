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
public class RightParenthesis {
	private char rpar;

	public RightParenthesis() {
		setRpar(' ');
	}

	public char getRpar() {
		return rpar;
	}

	public void setRpar(char rpar) {
		this.rpar = rpar;
	}

}