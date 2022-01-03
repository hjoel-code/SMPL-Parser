package smpl.lang.string;

import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.StringVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.type.simple.SMPLString;
import smpl.lang.arith.AIRExp;

public class SubString extends StringExp {
private StringExp string;
  private AIRExp arg1, arg2;

  public SubString(StringExp string, AIRExp arg1, AIRExp arg2) {
    this.string = string;
    this.arg1 = arg1;
    this.arg2 = arg2;
  }

  public StringExp getString() {
    return string;
  }

  public AIRExp getArg1() {
    return arg1;
  }

  public AIRExp getArg2() {
    return arg2;
  }

  @Override
  public SMPLString eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
      return (SMPLString) eval.eval(state, this);
  }

  @Override
  public <S, T> T visit(StringVisitor<StringExp, S, T> v, S state) throws SMPLException {
      return v.visitSubString(this, state);
  }
}