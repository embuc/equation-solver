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
public class LeftParenthesis {
	private char lpar;

	public LeftParenthesis() {
		setLpar(' ');
	}

	public char getLpar() {
		return lpar;
	}

	public void setLpar(char lpar) {
		this.lpar = lpar;
	}

}