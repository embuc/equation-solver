package se.emirbuc.solver;

/**
 * <p>Title: Inlämningsuppgift I </p>
 * <p>Description:T Lexikalisk evaluator</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Mah Datateknik</p>
 * @author Emir Bucalovic
 * @version 1.0
 */

public class NotLeftParException extends EvalException {
  private String errorMessage="NotLeftParException";
  public NotLeftParException() {
  }

  public String toString() {
    return errorMessage;
  }

}