package smpl.lang;
  
import smpl.sys.SMPLException;

public class SIRBinaryExp extends SIRExp {
  String operator;
  SIRExp exp1, exp2;

  public SIRBinaryExp(String operator, SIRExp exp1, SIRExp exp2) {
    this.operator = operator;
    this.exp1 = exp1;
    this.exp2 = exp2;
  }

  public String getOperator() {
    return operator;
  }

  public SIRExp getExp1() {
    return exp1;
  }

  public SIRExp getExp2() {
    return exp2;
  }

  @Override
  public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
    return v.visitSIRBinaryExp(this, state);
  }
}