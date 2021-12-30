package smpl.lang;

import smpl.sys.SMPLException;

public class SIRLit extends SIRExp {
  Integer intVal;

  public SIRLit(Integer intVal) {
    this.intVal = intVal;
  }

  public Integer getVal() {
    return intVal;
  }

  @Override
  public <S, T> T visit(SMPLVisitor<S, T> v, S arg) throws SMPLException {
    return v.visitSIRLit(this, arg);
  }
}