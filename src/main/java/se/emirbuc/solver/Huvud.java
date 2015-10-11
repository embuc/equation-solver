package se.emirbuc.solver;

import java.lang.Math;

/**
 * <p>Title: Inlämningsuppgift I </p>
 * <p>Description:T Lexikalisk evaluator</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Mah Datateknik</p>
 * @author Emir Bucalovic
 * @version 1.0
 */
public class Huvud {
  public String expression; //inmatade strängen
  public int index; //håller reda på index i indata
  long resultat = 0; //finalresultat (evaluerade uttrycket)

  /**
   * Default konstruktorn
   */
  public Huvud(String expression) {
	this.expression = expression;
  }

  /**
   * Konverterar String to Integer
   * @param s
   * @return int
   */
  public int stringToInt(String s) {
    int decim = 1, num = 0, current;
    for (int i = s.length() - 1; i >= 0; i--) {
      current = (int) s.charAt(i) - (int) ('0');
      num += current * decim;
      decim *= 10;
    }
    return num;
  }

  /**
   * Hämtar nästa talet i strängen indata
   * @return Objekt Tal
   * @throws NotANumberException
   */
  public Tal getNumber() throws NotANumberException {
    Tal tal = new Tal();
    boolean negativ = false;
    int num, first, last;
    String delString;

    while ( (expression.charAt(index) == ' ') && (!noMoreInput())) //ignorera blank
      index++;
    if (expression.charAt(index) == '-') {
      negativ = true; //kom ihåg att det är negativt tal och flytta indexet
      index++;
    }
    else if (expression.charAt(index) == '+')
      index++; //endast flytta indexet ett steg åt höger
    while ( (expression.charAt(index) == ' ') && (!noMoreInput())) //ignorera blank
      index++;
    first = index; //kom ihåg var första siffran börjar i delsträngen
    while ( ( (expression.charAt(index) == '0') | (expression.charAt(index) == '1') |
             (expression.charAt(index) == '2') |
             (expression.charAt(index) == '3') | (expression.charAt(index) == '4') |
             (expression.charAt(index) == '5') |
             (expression.charAt(index) == '6') | (expression.charAt(index) == '7') |
             (expression.charAt(index) == '8') |
             (expression.charAt(index) == '9')) && (!noMoreInput())) {
      index++;
    }
    last = index; //kom ihåg var siffran slutar
    if (isSiffra(expression.charAt(index)))
      delString = expression.substring(first, last + 1);
    else
      delString = expression.substring(first, last);
    tal.tal = this.stringToInt(delString);
    if (negativ)
      tal.tal = -tal.tal;
    return tal;
  }

  /**
   * Boolean metod som kollar om aktuella tecknet är en siffra
   * @param ch
   * @return true/false
   */
  public boolean isSiffra(char ch) {
    if ( (ch == '0') | (ch == '1') | (ch == '2') | (ch == '3') | (ch == '4') |
        (ch == '5') |
        (ch == '6') | (ch == '7') | (ch == '8') | (ch == '9'))
      return true;
    return false;
  }

  /**
   * Hämtar '+'
   * @return Objekt Plus
   * @throws NotAPlusException
   */
  public Plus getPlus() throws NotAPlusException {
    Plus pp = new Plus();
    while ( (expression.charAt(index) == ' ') && (!noMoreInput())) //ignorera blank
      index++;
    pp.plus = expression.charAt(index);
    index++;
    return pp;
  }

  /**
   * Hämtar '-'
   * @return Objekt Minus
   * @throws NotAMinusException
   */
  public Minus getMinus() throws NotAMinusException {
    Minus minus = new Minus();
    while ( (expression.charAt(index) == ' ') && (!noMoreInput())) //ignorera blank
      index++;
    minus.minus = expression.charAt(index);
    index++;
    return minus;
  }

  /**
   * Hämtar '*'
   * @return Objekt Mult
   * @throws NotAMultiplikationException
   */
  public Mult getMult() throws NotAMultiplikationException {
    Mult mult = new Mult();
    while ( (expression.charAt(index) == ' ') && (!noMoreInput())) //ignorera blank
      index++;
    mult.mult = expression.charAt(index);
    index++;
    return mult;
  }

  /**
   * Hämtar '/'
   * @return objekt Division
   * @throws NotADivisionException
   */
  public Division getDiv() throws NotADivisionException {
    Division div = new Division();
    while ( (expression.charAt(index) == ' ') && (!noMoreInput())) //ignorera blank
      index++;
    div.div = expression.charAt(index);
    index++;
    return div;
  }

  /**
   * Hämtar '^'
   * @return Objekt Exponent
   * @throws NotAExponentException
   */
  public Exponent getExp() throws NotAExponentException {
    Exponent exp = new Exponent();
    while ( (expression.charAt(index) == ' ') && (!noMoreInput())) //ignorera blank
      index++;
    exp.exp = expression.charAt(index);
    index++;
    return exp;
  }

  /**
   * Hämtar '!'
   * @throws NotAFakultetException
   */
  public void getFakultet() throws EvalException {
    while ( (expression.charAt(index) == ' ') && (!noMoreInput())) //ignorera blank
      index++;
    index++;
  }

  /**
   * Hämtar '('
   * @return Objekt LeftPar
   * @throws NotLeftParException
   */
  public LeftPar getLeftPar() throws NotLeftParException {
    LeftPar leftp = new LeftPar();
    while ( (expression.charAt(index) == ' ') && (!noMoreInput())) //ignorera blank
      index++;
    leftp.lpar = expression.charAt(index);
    index++;
    return leftp;
  }

  /**
   * Hämtar ')'
   * @return Objekt RightPar
   * @throws NotRightParException
   */
  public RightPar getRightPar() throws NotRightParException {
    RightPar rpar = new RightPar();
    while ( (expression.charAt(index) == ' ') && (!noMoreInput())) { //ignorera blank{
      index++;
    }
    rpar.rpar = expression.charAt(index);
    if (!noMoreInput())
      index++;
    return rpar;
  }

  /**
   * Kollar om nästa symbol är ett tal, pos. eller negativt
   * utan att egentligen läsa den
   * @return true/false
   */
  public boolean isNumber() {
    int local = index;
    try {
      char current = expression.charAt(local);
      while ( (expression.charAt(index) == ' ') && (!noMoreInput())) //ignorera blank
        index++;
        // hantera om siffran börjar med prefix + V -
      if ( (expression.charAt(index) == '+' | expression.charAt(index) == '-') &&
          !noMoreInput()) {
        index++;
        while ( (expression.charAt(index) == ' ') && (!noMoreInput()))
          index++;
      }
      //testa här isSiffra()
      while ( ( (expression.charAt(index) == '0') | (expression.charAt(index) == '1') |
               (expression.charAt(index) == '2') |
               (expression.charAt(index) == '3') | (expression.charAt(index) == '4') |
               (expression.charAt(index) == '5') |
               (expression.charAt(index) == '6') | (expression.charAt(index) == '7') |
               (expression.charAt(index) == '8') |
               (expression.charAt(index) == '9')) && (!noMoreInput())) {
        index++;
      }
      if ( (! (index == 0))) //om det finns tecken efter typ parantes eller blank
        if (noMoreInput() && isSiffra(expression.charAt(index)))
          current = expression.charAt(index); //om det är the very sista tecknet
        else
          current = expression.charAt(index - 1);
      else
        current = expression.charAt(index); //om det är the very sista tecknet
      index = local; //återställ indexet
      if ( (current == '0') | (current == '1') | (current == '2') |
          (current == '3') | (current == '4') | (current == '5') |
          (current == '6') | (current == '7') | (current == '8') |
          (current == '9'))
        return true; //om sista tecknet i travaseringen verkligen är en siffra då returnera true
    }
    catch (StringIndexOutOfBoundsException e) {
      System.out.println("Exception: " + e.getMessage());
    }
    return false; //annars returnera false
  }

  /**
   * Kollar om nästa symbol är "+" utan att egentligen läsa den
   * @return true/false
   */
  public boolean isPlus() {
    int local = index;
    try {
      while ( (expression.charAt(local) == ' ') && (!noMoreInput())) //ignorera blank
        local++;
      if (expression.charAt(local) == '+')
        return true;
    }
    catch (StringIndexOutOfBoundsException e) {}
    return false;
  }

  /**
   * Kollar om nästa symbol är "-" utan att egentligen läsa den
   * @return true/false
   */
  public boolean isMinus() {
    int local = index;
    try {
      while ( (expression.charAt(local) == ' ') && (!noMoreInput())) //ignorera blank
        local++;
      if (expression.charAt(local) == '-')
        return true;
    }
    catch (StringIndexOutOfBoundsException e) {}
    return false;
  }

  /**
   * Kollar om nästa symbol är *(" utan att egentligen läsa den
   * @return true/false
   */
  public boolean isMult() {
    int local = index;
    try {
      while ( (!noMoreInput()) && (expression.charAt(local) == ' ')) //ignorera blank
        local++;
      if (expression.charAt(local) == '*')
        return true;
    }
    catch (StringIndexOutOfBoundsException e) {}
    return false;
  }

  /**
   * Kollar om nästa symbol är "/" utan att egentligen läsa den
   * @return true/false
   */
  public boolean isDiv() {
    int local = index;
    try {
      while ( (!noMoreInput()) && (expression.charAt(local) == ' ')) //ignorera blank
        local++;
      if (expression.charAt(local) == '/')
        return true;
    }
    catch (StringIndexOutOfBoundsException e) {}
    return false;
  }

  /**
   * Kollar om nästa symbol är "^" utan att egentligen läsa den
   * @return true/false
   */
  public boolean isExp() {
    int local = index;
    try {
      while ( (!noMoreInput()) && (expression.charAt(local) == ' ')) //ignorera blank
        local++;
      if (expression.charAt(local) == '^')
        return true;
    }
    catch (StringIndexOutOfBoundsException e) {}
    return false;
  }

  /**
   * Kollar om nästa symbol är "!" utan att egentligen läsa den
   * @return true/false
   */
  public boolean isFak() {
    int local = index;
    try {
      while ( (!noMoreInput()) && (expression.charAt(local) == ' ')) //ignorera blank
        local++;
      if (expression.charAt(local) == '!')
        return true;
    }
    catch (StringIndexOutOfBoundsException e) {}
    return false;
  }

  /**
   * Kollar om nästa symbol är "(" utan att egentligen läsa den
   * @return true/false
   */
  public boolean isLeftPar() {
    int local = index;
    try {
      while ( (expression.charAt(local) == ' ') && (!noMoreInput())) //ignorera blank
        local++;
      if (expression.charAt(local) == '(')
        return true;
    }
    catch (StringIndexOutOfBoundsException e) {}
    return false;
  }

  /**
   * Kollar om nästa symbol är ")" utan att egentligen läsa den
   * @return true/false
   */
  public boolean isRightPar() {
    int local = index;
    try {
      while ( (expression.charAt(local) == ' ') && (!noMoreInput())) //ignorera blank
        local++;
      if (expression.charAt(local) == ')')
        return true;
    }
    catch (StringIndexOutOfBoundsException e) {}
    return false;
  }

  /**
   *  Kollar om det är slut på strängen - kom ihåg att den kollar "i förväg"!
   * @return true/false
   */
  public boolean noMoreInput() {
    if ( (index + 1) >= expression.length())
      return true;
    return false;
  }

  /**
   * gramatik regeln "start"
   * @return delresultat och såsmåningom finalresultat
   * @throws EvalException
   */
  public long start() throws EvalException {
    long i;
    i = t();
    if (isPlus())
      return x() + i;
    else if (isMinus())
      return i - w();
    else if (!noMoreInput() && !isRightPar())
      throw new ExpectingRightParOrEndException();
    return i;
  }

  /**
   * gramatik regeln "x"
   * @return addition
   * @throws EvalException
   */
  public long x() throws EvalException {
    long i;
    try {
      getPlus();
      i = t(); //börja om dvs söka vänster associativa...
      if (isPlus())
        return x() + i;
      return i;
    }
    catch (NotAPlusException e) {
      throw e;
    }
  }

  /**
   * gramatik regeln "w"
   * @return subtraktion
   * @throws EvalException
   */
  public long w() throws EvalException {
    long i = 0;
    try {
      getMinus();
      i = t();
      if (isMinus())
        return i + w();//en fuling + :)
      return i;
    }
    catch (NotAMinusException e) {
      throw e;
    }
  }

  /**
   * gramatik regeln "e"
   * @return talet upphöjd till exponenten, "^"
   * @throws EvalException
   */
  public long e() throws EvalException {
    long i = 0;
    try {
      getExp();
      i = f();
      if (isExp())
        return (long) Math.pow( (long) i, (long) e());
    }
    catch (NotAExponentException e) {
      throw e;
    }
    return i;
  }

  /**
   * gramatik regeln "cF"
   * @return delresultat, fakultet
   * @throws EvalException
   */
  public long cF(long i) throws EvalException {
    long res = 1; //definition säger att 0!=1
    try {
      getFakultet();
      for (long j = i; i > 1; i--)
        res *= i;
    }
    catch (NotAFakultetException e) {
      throw e;
    }
    return res;
  }

  /**
   * gramatik regeln "t"
   * @return delresultat, multiplikation,division,exp,fak!
   * @throws EvalException
   */
  public long t() throws EvalException { //prioritet 1 - kolla om mult eller div
    long i;
    i = f();
    if (isFak()) {
      try {
        i = cF(i);
      }
      catch (NotAFakultetException e) {
        throw e;
      }
    }
    if (isExp())
      i = (long) Math.pow( (long) i, (long) e());
    if (isMult())
      return y() * i;
    else if (isDiv())
      try {
        return i / d(); //här kan exception hända - division med noll t. ex.
      }
      catch (ArithmeticException e) {
        System.out.println("ArithmeticException: " + e.getMessage());
      }
    else if ( (!noMoreInput()) && (!isRightPar()) && (!isPlus()) && (!isMinus()))
      throw new ExpectedPlusRightParOrEndException();
    return i;
  }

  /**
   * gramatik regeln "y"
   * @return delresultat, multiplikation
   * @throws EvalException
   */
  public long y() throws EvalException {
    long i = 0;
    try {
      getMult();
      i = t();
      if (isMult())
        return y() * i;
    }
    catch (NotAMultiplikationException e) {
      throw e;
    }
    return i;
  }

  /**
   * gramatik regeln "d"
   * @return delresultat, division
   * @throws EvalException
   */
  public long d() throws EvalException {
    long i = 0;
    try {
      getDiv();
      i = t();
      if (isDiv())
        return i / d();
    }
    catch (NotADivisionException e) {
      throw e;
    }
    return i;
  }

  /**
   * gramatik regeln "f"
   * @return ett tal, eller delresultat (inom parantes)
   * @throws EvalException
   */
  public long f() throws EvalException { //get number eller parantes
    long i = 0;
    if (isLeftPar()) {
      try {
        getLeftPar();
        i = start();
        getRightPar();
      }
      catch (NotRightParException x) {
        throw x;
      }
    }
    else if (isNumber()) {
      try {
        i = getNumber().tal;
      }
      catch (NotANumberException e) {
        throw e;
      }
    }
    else
      throw new ExpectedLeftParOrNumberException();
    return i;
  }

  /**
   * Main Funktion
   * @param args
   */
  /******************************* main() **********************************/
  public static void main(String[] args) {
    /*
     String från uppgiften som skall evalueras:

     rätta resultatet: 16778548
     */
//    Huvud huvud1 = new Huvud();
//    long Resultat = 0;
//    huvud1.indata = Input.readLine("Mata in ett utryck för evaluering: " +
//                                   "\n");
//    System.out.println(huvud1.indata);
//    try {
//      Resultat = huvud1.start();
//      System.out.println("Resultat= " + Resultat);
//    }
//    catch (EvalException e) {
//      System.out.println("Felet: " + e.toString());
//    }
//    System.exit(0);
  }
  /***************************** end of main() *****************************/
}