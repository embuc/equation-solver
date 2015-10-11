package se.emirbuc.solver;

/**
 * <p>Title: Inl�mningsuppgift I </p>
 * <p>Description:T Lexikalisk evaluator</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Mah Datateknik</p>
 * @author Emir Bucalovic
 * @version 1.0
 */

public class NotAPlusException
    extends EvalException {
  private String errorMessage="NotAPlusException";
  public NotAPlusException() {
  }

  public String toString() {
    return errorMessage;
  }

}