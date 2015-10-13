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
public class Number {
	private int number;

	public Number() {
		setNumber(0);
	}

	@Override
	public String toString() {
		return String.valueOf(getNumber());
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void invertTheSignOfNumber() {
		this.number = -this.number;

	}
}