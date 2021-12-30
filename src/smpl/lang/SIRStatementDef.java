package smpl.lang;

import smpl.sys.*;

public class SIRStatementDef extends SIRStatement {
  public String id;
  public SIRExp exp;

  public SIRStatementDef(String id, SIRExp exp) {
    this.id = id;
    this.exp = exp;
  }

  public String getId() {
    return id;
  }
  
  public SIRExp getExp() {
    return exp;
  }

  @Override
  public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
    return v.visitSIRStatementDef(this, state);
  }
}