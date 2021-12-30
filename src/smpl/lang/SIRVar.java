package smpl.lang;

import smpl.sys.SMPLException;

public class SIRVar extends SIRExp {
  String var;

  public SIRVar(String id) {
    var = id;
  }

  public String getId() {
    return var;
  }

  @Override
  public <S, T> T visit(SMPLVisitor<S, T> v, S arg) throws SMPLException {
    return v.visitSIRVar(this, arg);
  }
}