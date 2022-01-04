package smpl.lang.arith;

import smpl.sys.*;
import smpl.lang.SIRExp;
import smpl.lang.SIRObj;
import smpl.lang.visitors.*;

public abstract class AIRExp extends SIRExp<AIRExp> implements SIRObj {

  private final String type;

  public AIRExp() {
    this.type = "arith";
  }

  @Override
  public String getType() {
      return type;
  }

  public abstract <S, T> T visit(AIRVisitor<AIRExp, S, T> v, S state) throws SMPLException;

  @Override
  public <S, T> T visit(SIRVisitor<AIRExp, S, T> v, S state) throws SMPLException {
    return visit((AIRVisitor<AIRExp, S, T>) v, state);
  }

  @Override
  public String toString() {
      return "Arithmetic Expression";
  }

}
