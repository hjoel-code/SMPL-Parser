package smpl.lang.string;

import smpl.lang.SIRVar;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.StringVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.type.simple.SMPLString;
import smpl.lang.arith.AIRExp;

public class StringConcat extends StringExp {
private StringExp string1, string2;

  public StringConcat(StringExp string1, StringExp string2) {
    this.string1 = string1;
    this.string2 = string2;
  }

  public StringExp getArg1() {
    return string1;
  }

  public StringExp getArg2() {
    return string2;
  }

  @Override
  public SMPLString eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
      return (SMPLString) eval.eval(state, this);
  }

  @Override
  public <S, T> T visit(StringVisitor<StringExp, S, T> v, S state) throws SMPLException {
      return v.visitStringConcat(this, state);
  }
}