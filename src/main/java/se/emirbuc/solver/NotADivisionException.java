package se.emirbuc.solver;

/**
 * <p>Title: Inlämningsuppgift I </p>
 * <p>Description:T Lexikalisk evaluator</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Mah Datateknik</p>
 * @author Emir Bucalovic
 * @version 1.0
 */

public class NotADivisionException extends EvalException {
  private String errorMessage="NotADivisionException";
  public NotADivisionException() {
  }

  public String toString() {
    return errorMessage;
  }

}