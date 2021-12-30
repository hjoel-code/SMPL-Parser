package smpl.lang;

import smpl.sys.*;
import smpl.values.*;

public enum SIRBuiltInFun {
  PAIR("pair") {
    public Primitive apply(SMPLContext env, SMPLVisitor v) {
      System.out.print("I am a pair function");

      // Todo: finish pair implementation
      return Primitive.DEFAULT;
    }
  },
  // To Do : Add other built-in functions here
  ;

  String symbol;

  SIRBuiltInFun(String symbol) {
    this.symbol = symbol;
  }

  public abstract Primitive apply(SMPLContext env, SMPLVisitor v);

  /**
   * @return The symbol for this operator
   */
  public String getSymbol() {
    return symbol;
  }
}